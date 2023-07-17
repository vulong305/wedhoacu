package webhoacu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webhoacu.spring.model.NhanVien;


@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long>{

	//Custom query
	@Query(value = "SELECT * FROM nhanvien nv WHERE nv.MaNV like %:keyword% or nv.HoTen like %:keyword% or nv.Email like %:keyword% or nv.SDT like %:keyword% or nv.DiaChi like %:keyword% or nv.CMND like %:keyword% or nv.TaiKhoan like %:keyword%", nativeQuery = true)
	List<NhanVien> findByKeyword(@Param("keyword") String keyword);

	@Query("SELECT nv FROM NhanVien nv WHERE nv.email = ?1")
	public NhanVien findByEmail(String email);
	
	@Query("SELECT nv FROM NhanVien nv WHERE nv.taiKhoan = ?1")
	public NhanVien findByTaiKhoan(String taikhoan);
	
}
