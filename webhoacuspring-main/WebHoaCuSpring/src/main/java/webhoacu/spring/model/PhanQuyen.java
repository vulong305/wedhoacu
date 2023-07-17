package webhoacu.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "nhanvien_quyen")
//@IdClass(PhanQuyen.class)
public class PhanQuyen implements Serializable{

	  @Id
	  @Column(name = "nhanvien_id")
	  private long maNV;

	/* Tránh viết cả 2 trùng nhau sẽ bị lỗi */
//	  @Id
	  @Column(name = "quyen_id")
	  private long maQuyen;

	public long getMaNV() {
		return maNV;
	}

	public void setMaNV(long maNV) {
		this.maNV = maNV;
	}

	public long getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(long maQuyen) {
		this.maQuyen = maQuyen;
	}

	  

}
