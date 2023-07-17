package webhoacu.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webhoacu.spring.model.ChiTietHoaDon;



@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Long> {
	//Custom query
	@Query(value = "SELECT * FROM chitiethoadon cthd WHERE cthd.MaHD like %:keyword% or cthd.MaSP like %:keyword% or cthd.SoLuong like %:keyword% or cthd.DonGia like %:keyword%", nativeQuery = true)
	List<ChiTietHoaDon> findByKeyword(@Param("keyword") String keyword);
	
	@Query(value = "SELECT * FROM chitiethoadon cthd WHERE cthd.MaHD = :maHD", nativeQuery = true )
	List<ChiTietHoaDon> fintByMaHD(@Param("maHD") long maHD);
	
    @Modifying
    @Query(value = "INSERT INTO chitiethoadon(MaHD,MaSP, SoLuong, DonGia) VALUES (:maHD,:maSP,:soLuong,:donGia)", nativeQuery = true)
    @Transactional
    void addCTHD(@Param("maHD") long maHD, @Param("maSP") long maSP,@Param("soLuong") long soLuong,@Param("donGia") long donGia);
    
    
}
