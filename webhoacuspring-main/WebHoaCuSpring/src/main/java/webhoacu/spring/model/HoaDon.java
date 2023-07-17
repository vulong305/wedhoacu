package webhoacu.spring.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "hoadon")
public class HoaDon{
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long maHD;

	/* Tránh viết cả 2 trùng nhau sẽ bị lỗi */
	  @Column(name = "SHD")
	  private String shd;
	  
	/* Bỏ @DateTimeFormat vào sẽ lỗi không New với Update Hoá Đơn mới được */
	  @Column(name = "NgayDat")
	  private Date ngayDat;

	  @Column(name = "NgayGiao")
	  private Date ngayGiao;
	  
	  @Column(name = "DiaChiNhan")
	  private String diaChiNhan;
	  
	  @Column(name = "TinhTrang")
	  private String tinhTrang;
	  
	  @Column(name = "MaKH")
	  private long maKH;

	public long getMaHD() {
		return maHD;
	}

	public void setMaHD(long maHD) {
		this.maHD = maHD;
	}

	public String getShd() {
		return shd;
	}

	public void setShd(String shd) {
		this.shd = shd;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public Date getNgayGiao() {
		return ngayGiao;
	}

	public void setNgayGiao(Date ngayGiao) {
		this.ngayGiao = ngayGiao;
	}

	public String getDiaChiNhan() {
		return diaChiNhan;
	}

	public void setDiaChiNhan(String diaChiNhan) {
		this.diaChiNhan = diaChiNhan;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public long getMaKH() {
		return maKH;
	}

	public void setMaKH(long maKH) {
		this.maKH = maKH;
	}

	public HoaDon() {
		
	}

	

	

}
