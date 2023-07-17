package webhoacu.spring.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name = "quyen")
public class Quyen{
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long maQuyen;

	/* Tránh viết cả 2 trùng nhau sẽ bị lỗi */
	  @Column(name = "TenQuyen")
	  private String tenQuyen;

	public long getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(long maQuyen) {
		this.maQuyen = maQuyen;
	}

	public String getTenQuyen() {
		return tenQuyen;
	}

	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}
	  
	  
}
