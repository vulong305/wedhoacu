package webhoacu.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(ChiTietHoaDonId.class)
@Table(name = "chitiethoadon")
public class ChiTietHoaDon{
	
	  @Id
	  @Column(name = "MaHD")
	  private long maHD;

	/* Tránh viết cả 2 trùng nhau sẽ bị lỗi */
	  
	  @Id
	  @Column(name = "MaSP")
	  private long maSP;

	  @Column(name = "SoLuong")
	  private long soLuong;

	  @Column(name = "donGia")
	  private long donGia;

	public long getMaHD() {
		return maHD;
	}

	public void setMaHD(long maHD) {
		this.maHD = maHD;
	}

	public long getMaSP() {
		return maSP;
	}

	public void setMaSP(long maSP) {
		this.maSP = maSP;
	}

	public long getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(long soLuong) {
		this.soLuong = soLuong;
	}

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}

	public ChiTietHoaDon(long maHD, long maSP, long soLuong, long donGia) {
		
		this.maHD = maHD;
		this.maSP = maSP;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public ChiTietHoaDon() {
		
	}
	  
	  
	  

}
