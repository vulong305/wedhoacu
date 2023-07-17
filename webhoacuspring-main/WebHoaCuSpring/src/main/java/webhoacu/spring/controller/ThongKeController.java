package webhoacu.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import webhoacu.spring.model.HoaDon;
import webhoacu.spring.model.SanPham;
import webhoacu.spring.repository.KhachHangRepository;
import webhoacu.spring.repository.NhanVienRepository;
import webhoacu.spring.repository.SanPhamRepository;
import webhoacu.spring.repository.HoaDonRepository;
import webhoacu.spring.service.SanPhamService;

@Controller
public class ThongKeController {

	@Autowired
	private SanPhamService SanPhamService;

	@Autowired
	private SanPhamRepository SanPhamRepository;
	
	@Autowired
	private KhachHangRepository KhachHangRepository;
	
	@Autowired
	private NhanVienRepository NhanVienRepository;
	
	@Autowired
	private HoaDonRepository HoaDonRepository;
	
	@GetMapping("/page_thongke")
	public String viewPageThongKe(Model model) {
		model.addAttribute("countSP", SanPhamRepository.count());
		model.addAttribute("countKH", KhachHangRepository.count());
		model.addAttribute("countNV", NhanVienRepository.count());
		model.addAttribute("countHD", HoaDonRepository.count());
		model.addAttribute("tongDoanhThu", SanPhamRepository.tongDoanhThu());
		String Date = java.time.LocalDate.now().toString();
		model.addAttribute("date",Date);
		model.addAttribute("month","2022-06");
		model.addAttribute("tongDoanhThu_Ngay", SanPhamService.tongDoanhThu_Ngay(Date));
		model.addAttribute("tongDoanhThu_Thang", SanPhamService.tongDoanhThu_Thang("2022-06"));
		return "admin/page_thongke";
	}
	
	
	@RequestMapping({ "/searchDoanhThu_Ngay" })
	public String searchDoanhThu_Ngay(HoaDon HoaDon, Model model, String date) {
		if (date != null) {
			model.addAttribute("countSP", SanPhamRepository.count());
			model.addAttribute("countKH", KhachHangRepository.count());
			model.addAttribute("countNV", NhanVienRepository.count());
			model.addAttribute("countHD", HoaDonRepository.count());
			model.addAttribute("tongDoanhThu", SanPhamRepository.tongDoanhThu());
			String tongDoanhThuNgay = SanPhamService.tongDoanhThu_Ngay(date);
			int tongDTNgay = 0;
			if (tongDoanhThuNgay != null) {
				tongDTNgay = Integer.parseInt(tongDoanhThuNgay);
			}
			model.addAttribute("date",date);
			model.addAttribute("tongDoanhThu_Ngay", tongDTNgay);
		}
		else {
			return "admin/page_thongke";
		}
		return "admin/page_thongke";
	}
	
	@RequestMapping({ "/searchDoanhThu_Thang" })
	public String searchDoanhThu_Thang(HoaDon HoaDon, Model model, String month) {
		if (month != null) {
			model.addAttribute("countSP", SanPhamRepository.count());
			model.addAttribute("countKH", KhachHangRepository.count());
			model.addAttribute("countNV", NhanVienRepository.count());
			model.addAttribute("countHD", HoaDonRepository.count());
			model.addAttribute("tongDoanhThu", SanPhamRepository.tongDoanhThu());
			String tongDoanhThuThang = SanPhamService.tongDoanhThu_Thang(month);
			int tongDTThang = 0;
			if (tongDoanhThuThang != null) {
				tongDTThang = Integer.parseInt(tongDoanhThuThang);
			}
			model.addAttribute("month",month);
			model.addAttribute("tongDoanhThu_Thang", tongDTThang);
		}
		else {
			return "admin/page_thongke";
		}
		return "admin/page_thongke";
	}
	
}
