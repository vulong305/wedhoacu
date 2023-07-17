package webhoacu.spring.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import webhoacu.spring.model.GioHangItem;
import webhoacu.spring.model.SanPham;
import webhoacu.spring.service.SanPhamService;

@Repository
public class GioHangRepository {
	
	@Autowired
	private SanPhamService sanPhamService;
	
	//Thêm item vào giỏ hàng
	public HashMap<Long,GioHangItem> AddGioHang(long id,int soLuong, HashMap<Long,GioHangItem> gioHang) {
		GioHangItem gioHangItem = new GioHangItem();
		SanPham sanPham = sanPhamService.getSanPhamById(id);
		
		if (sanPham != null && gioHang.containsKey(id)) {
			gioHangItem = gioHang.get(id);
			//Khi đã có thì tăng số lượng theo quantity lấy vào
			int soLuongSP = gioHangItem.getSoLuong() + soLuong;
			gioHangItem.setSoLuong(soLuongSP);
			int tongTien = gioHangItem.getSoLuong() * gioHangItem.getSanPham().getDonGia();
			gioHangItem.setTongTien(tongTien);
			
		}else {
			gioHangItem.setSanPham(sanPham);
			gioHangItem.setSoLuong(soLuong);
			
			long tongTien = sanPham.getDonGia() * soLuong;
			gioHangItem.setTongTien(tongTien);
		}
		
		gioHang.put(id, gioHangItem);
		return gioHang;
	}
		
	//Chỉnh sửa item trong giỏ hàng
	public HashMap<Long,GioHangItem> EditGioHang(long id,int soLuong, HashMap<Long,GioHangItem> gioHang) {
		if (gioHang == null) {
			return gioHang;
		}
		
		GioHangItem gioHangItem = new GioHangItem();
		
		if (gioHang.containsKey(id)) {
			gioHangItem = gioHang.get(id);
			gioHangItem.setSoLuong(soLuong);
			//Số lượng hiện có nhân với đơn giá sản phẩm
			int tongTien = soLuong * gioHangItem.getSanPham().getDonGia();
			gioHangItem.setTongTien(tongTien);
		}

		gioHang.put(id, gioHangItem);
		return gioHang;
	}
	
	//Xóa item trong giỏ hàng
	public HashMap<Long,GioHangItem> DeleteGioHang(long id, HashMap<Long,GioHangItem> gioHang) {
		if (gioHang == null) {
			return gioHang;
		}
		if (gioHang.containsKey(id)) {
			gioHang.remove(id);
		}

		return gioHang;
	}
	
	//Tính tổng số lượng sản phẩm trong giỏ hàng
	public int TotalQuantity(HashMap<Long,GioHangItem> gioHang) {
		int tongSoLuong = 0;
		for(Map.Entry<Long, GioHangItem> gioHangItem : gioHang.entrySet()) {
			tongSoLuong += gioHangItem.getValue().getSoLuong();
		}
		return tongSoLuong;
	}
	
	//Tính tổng giá trong giỏ hàng
	public int TotalPrice(HashMap<Long,GioHangItem> gioHang) {
		int tongTienGioHang = 0;
		for(Map.Entry<Long, GioHangItem> gioHangItem : gioHang.entrySet()) {
			tongTienGioHang += gioHangItem.getValue().getTongTien();
		}
		return tongTienGioHang;
	}
	
}
