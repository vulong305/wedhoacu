package webhoacu.spring.model;


public class GioHangItem {
	private int soLuong;
	private long tongTien;
	private SanPham sanPham;
	
	
	public GioHangItem() {
	}
	
	public GioHangItem(int soLuong, long tongTien, SanPham sanPham) {
		this.soLuong = soLuong;
		this.tongTien = tongTien;
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public long getTongTien() {
		return tongTien;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public void setTongTien(long tongTien) {
		this.tongTien = tongTien;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	

	
	
}
