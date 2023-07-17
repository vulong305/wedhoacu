package webhoacu.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "loaisanpham")
public class LoaiSanPham {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long maLoaiSP;

	/* Tránh viết cả 2 trùng nhau sẽ bị lỗi */
	  @NotBlank(message = "Tên loại sản phẩm không được để trống")
	  @Column(name = "TenLoaiSP")
	  private String tenLoaiSP;

	public LoaiSanPham(long maLoaiSP, String tenLoaiSP) {
		this.maLoaiSP = maLoaiSP;
		this.tenLoaiSP = tenLoaiSP;
	}

	public LoaiSanPham() {
	}

	public long getMaLoaiSP() {
		return maLoaiSP;
	}

	public void setMaLoaiSP(long maLoaiSP) {
		this.maLoaiSP = maLoaiSP;
	}

	public String getTenLoaiSP() {
		return tenLoaiSP;
	}

	public void setTenLoaiSP(String tenLoaiSP) {
		this.tenLoaiSP = tenLoaiSP;
	}

}
