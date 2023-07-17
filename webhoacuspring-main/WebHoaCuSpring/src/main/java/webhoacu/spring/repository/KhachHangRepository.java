package webhoacu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webhoacu.spring.model.KhachHang;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long>  {
	 //Custom query
	 @Query(value = "SELECT * FROM khachhang kh WHERE kh.MaKH like %:keyword% or kh.HoTen like %:keyword% or kh.Email like %:keyword% or kh.SDT like %:keyword% or kh.DiaChi like %:keyword% ", nativeQuery = true)
	 List<KhachHang> findByKeyword(@Param("keyword") String keyword);
	 
	 @Query(value="SELECT MAX(MaKH) from khachhang",nativeQuery = true)
	 long LastIdKhachHang();
}
