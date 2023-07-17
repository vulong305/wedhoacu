package webhoacu.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "hangsanxuat")
public class HangSanXuat {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long maHangSX;
	  
	  @NotBlank(message = "Tên hãng sản xuất không được để trống")
	  @Column(name = "TenHangSX")
	  private String tenHangSX;
	  
	  @NotBlank(message = "Đặc điểm hãng sản xuất không được để trống")
	  @Column(name = "DacDiem")
	  private String dacDiem;

	public long getMaHangSX() {
		return maHangSX;
	}

	public void setMaHangSX(long maHangSX) {
		this.maHangSX = maHangSX;
	}

	public String getTenHangSX() {
		return tenHangSX;
	}

	public void setTenHangSX(String tenHangSX) {
		this.tenHangSX = tenHangSX;
	}

	public String getDacDiem() {
		return dacDiem;
	}

	public void setDacDiem(String dacDiem) {
		this.dacDiem = dacDiem;
	}

	public HangSanXuat() {
		
	}
	  
	
}
