package webhoacu.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "nhanvien")
public class NhanVien {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long maNV;
	  
	  /* Tránh viết cả 2 trùng nhau sẽ bị lỗi */
	  @NotBlank(message = "Họ tên không được để trống")
	  @Column(name = "HoTen")
	  private String hoTen;

	  @NotBlank(message = "Email không được để trống")
	  @Email(message = "Nhập đúng định dạng Email")
	  @Column(name = "Email")
	  private String email;

	  @NotBlank(message = "SDT không được để trống")
	  @Pattern(regexp="^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", message="Số điện thoại không hợp lệ") /* Validate SĐT nhập đủ 10 số */
	  @Column(name = "SDT")
	  private String sdt;
	  
	  @NotBlank(message = "Đỉa chỉ không được để trống")
	  @Column(name = "DiaChi")
	  private String diaChi;
	  
	 
	  @NotBlank(message = "CMND không được để trống")
	  @Size(min = 9, max = 12, message ="Độ dài kí tự từ 9 đến 12 ")
	  @Column(name = "CMND")
	  private String cmnd;
	  
	  @NotBlank(message = "Tài khoản không được để trống")
	  @Size(min = 3, max = 20, message ="Độ dài kí tự từ 3 đến 20")
	  @Column(name = "TaiKhoan")
	  private String taiKhoan;
	  
	  @NotBlank(message = "Mật khẩu không được để trống")
	  @Size(min = 6, max = 150, message ="Độ dài kí tự từ 6 đến 150")
	  @Column(name = "MatKhau")
	  private String matKhau;
	  
	  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  @JoinTable(
			  name = "nhanvien_quyen",
	          joinColumns = @JoinColumn(name = "nhanvien_id"),
	          inverseJoinColumns = @JoinColumn(name = "quyen_id")
			  )
	  private Set<Quyen> quyens = new HashSet<>();
	  
	public Set<Quyen> getQuyens() {
		return quyens;
	}

	public void setQuyens(Set<Quyen> quyens) {
		this.quyens = quyens;
	}

	public long getMaNV() {
		return maNV;
	}

	public void setMaNV(long maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
}
