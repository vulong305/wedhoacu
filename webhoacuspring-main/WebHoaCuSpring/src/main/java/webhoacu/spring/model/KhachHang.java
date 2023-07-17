package webhoacu.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "khachhang")
public class KhachHang {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long maKH;

	public KhachHang() {
		
	}

	/* Tránh viết cả 2 trùng nhau sẽ bị lỗi */
	  @NotBlank(message = "Họ tên không được để trống")
	  @Column(name = "HoTen")
	  private String hoTen;

	  @NotBlank(message = "Email không được để trống")
	  @Email(message = "Email không hợp lệ")
	  @Column(name = "Email")
	  private String email;

	  @NotBlank(message = "SĐT không được để trống")
	  @Pattern(regexp="^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message="Số điện thoại không hợp lệ") /* Validate SĐT nhập đủ 10 số */
	  @Column(name = "SDT")
	  private String sdt;
	  
	  @NotBlank(message = "Địa chỉ không được để trống")
	  @Column(name = "DiaChi")
	  private String diaChi;

	public long getMaKH() {
		return maKH;
	}

	public String getHoTen() {
		return hoTen;
	}

	public String getEmail() {
		return email;
	}

	public String getSdt() {
		return sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setMaKH(long maKH) {
		this.maKH = maKH;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	

	

	  
}
