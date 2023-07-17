package webhoacu.spring.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import webhoacu.spring.model.ChiTietHoaDon;
import webhoacu.spring.model.GioHangItem;
import webhoacu.spring.model.HoaDon;
import webhoacu.spring.model.KhachHang;
import webhoacu.spring.service.ChiTietHoaDonService;
import webhoacu.spring.service.GioHangService;
import webhoacu.spring.service.HangSanXuatService;
import webhoacu.spring.service.HoaDonService;
import webhoacu.spring.service.KhachHangService;
import webhoacu.spring.service.LoaiSanPhamService;
import webhoacu.spring.service.SanPhamService;

@Controller
public class GioHangController {
	@Autowired
	private SanPhamService sanPhamService;

	@Autowired
	private KhachHangService khachHangService;
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@Autowired
	private HangSanXuatService hangSanXuatService; 
	
	@Autowired
	private GioHangService gioHangService;
	
	@Autowired
	private HoaDonService hoaDonService;
	
	@Autowired
	private ChiTietHoaDonService chiTietHoaDonService;
	
	@GetMapping("/cart")
	public String ShopCart(Model model) {
		model.addAttribute("listLoaiSanPhams", loaiSanPhamService.getAllLoaiSanPham());
		model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
		return "user/shopcart";
	}
	
	@GetMapping("/addcart/{id}/{quantity}")
	public String AddCart(HttpServletRequest request, HttpSession session, @PathVariable(value = "id") long id, 
			@PathVariable(value = "quantity") int quantity, Model model) {
		HashMap<Long,GioHangItem> gioHang = (HashMap<Long,GioHangItem>)session.getAttribute("GioHang");
		if (gioHang == null) {
			gioHang = new HashMap<Long,GioHangItem>();
		}
		gioHang = gioHangService.AddGioHang(id,quantity, gioHang);
		session.setAttribute("GioHang", gioHang);
		session.setAttribute("TongTienGioHang", gioHangService.TotalPrice(gioHang));
		session.setAttribute("TongSLGioHang", gioHangService.TotalQuantity(gioHang));
		return "redirect:" + request.getHeader("Referer");//Refresh lại trang hiện tại
	}
	
	@GetMapping("/editcart/{id}/{quantity}")
	public String EditCart(HttpServletRequest request, HttpSession session, @PathVariable(value = "id") long id,@PathVariable(value = "quantity") int quantity,Model model) {
		HashMap<Long,GioHangItem> gioHang = (HashMap<Long,GioHangItem>)session.getAttribute("GioHang");
		if (gioHang == null) {
			gioHang = new HashMap<Long,GioHangItem>();
		}
		
		gioHang = gioHangService.EditGioHang(id,quantity, gioHang);
		session.setAttribute("GioHang", gioHang);
		session.setAttribute("TongTienGioHang", gioHangService.TotalPrice(gioHang));
		session.setAttribute("TongSLGioHang", gioHangService.TotalQuantity(gioHang));
		return "redirect:" + request.getHeader("Referer");//Refresh lại trang hiện tại
	}
	
	@GetMapping("/Deletecart/{id}")
	public String DeleteCart(HttpServletRequest request, HttpSession session, @PathVariable(value = "id") long id,Model model) {
		HashMap<Long,GioHangItem> gioHang = (HashMap<Long,GioHangItem>)session.getAttribute("GioHang");
		if (gioHang == null) {
			gioHang = new HashMap<Long,GioHangItem>();
		}
		gioHang = gioHangService.DeleteGioHang(id, gioHang);
		session.setAttribute("GioHang", gioHang);
		session.setAttribute("TongTienGioHang", gioHangService.TotalPrice(gioHang));
		session.setAttribute("TongSLGioHang", gioHangService.TotalQuantity(gioHang));
		return "redirect:" + request.getHeader("Referer");//Refresh lại trang hiện tại
	}
	
	
	@GetMapping("/paybill")
	public String PayTheBill(Model model) {
		KhachHang khachhang = new KhachHang();
	    model.addAttribute("khachhang", khachhang);
		model.addAttribute("listLoaiSanPhams", loaiSanPhamService.getAllLoaiSanPham());
		model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
		return "user/paybill";
	}
	
	@GetMapping("/checkInsert")
	public String Check(HttpServletRequest request) {
		return "redirect:" + request.getHeader("Referer");
	}
	
//	@GetMapping("/paydone")
//	public String Done() {
//		return "user/paydone";
//	}
	
	@PostMapping("/checkoutbill")
	public String CheckOutBill(HttpServletRequest request, HttpSession session,
			@ModelAttribute("khachhang") @Valid  KhachHang khachhang, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) 
		{
			return "user/paybill";
		} else {
			List<KhachHang> listKHs = khachHangService.getAllKhachHang();
			boolean chooseMaKH = false;
			long maKH = 1;
			
			//Nếu có khách hàng trùng tên,email,sdt,diachi với khách hàng có trong database thì lấy khách hàng trong database
			for(int i=0;i<listKHs.size(); i++) {
				if (khachhang.getHoTen().equals(listKHs.get(i).getHoTen())
					&& khachhang.getEmail().equals(listKHs.get(i).getEmail())	
					&& khachhang.getSdt().equals(listKHs.get(i).getSdt())
					&& khachhang.getDiaChi().equals(listKHs.get(i).getDiaChi())
					) 
				{
					maKH = listKHs.get(i).getMaKH();
					chooseMaKH = true;
					break;
				}
			}
			
			if (chooseMaKH == false) {
				khachHangService.saveKhachHang(khachhang);
				maKH = khachHangService.getLastIdKhachHang();
			}
			HoaDon hoaDon = new HoaDon();
			//Số hóa đơn
			long millis=System.currentTimeMillis();  
			java.sql.Date today=new java.sql.Date(millis); 
			String toDayString = today.toString().replaceAll("-","");
			long lastIDHoaDon = hoaDonService.getLastIdHoaDon() + 1;
			String soHD = "HD" + toDayString + "0" + lastIDHoaDon;
			hoaDon.setShd(soHD);
			//Ngày đặt 
			hoaDon.setNgayDat(today);
			//Địa chỉ nhận
			String diaChi = khachHangService.getKhachHangById(maKH).getDiaChi();
			hoaDon.setDiaChiNhan(diaChi);
			//Tình trạng
			hoaDon.setTinhTrang("Chưa xác nhận");
			//Mã khách hàng
			hoaDon.setMaKH(maKH);
			//Lưu hóa đơn
			hoaDonService.saveHoaDon(hoaDon);
			//Lưu chi tiết hóa đơn
			long maHD = hoaDonService.getLastIdHoaDon();
			HashMap<Long,GioHangItem> gioHang = (HashMap<Long,GioHangItem>)session.getAttribute("GioHang");
			for(Map.Entry<Long, GioHangItem> gioHangItem : gioHang.entrySet()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				cthd.setMaHD(maHD);
				cthd.setMaSP(gioHangItem.getValue().getSanPham().getMaSP());
				cthd.setSoLuong(gioHangItem.getValue().getSoLuong());
				cthd.setDonGia(gioHangItem.getValue().getSanPham().getDonGia());
				
				chiTietHoaDonService.addChiTietHoaDonUseQuery(cthd);
			}
			//Xóa session
			session.removeAttribute("GioHang");
			session.removeAttribute("TongTienGioHang");
			session.removeAttribute("TongSLGioHang");
			
			return "user/paydone";
		}}
}
