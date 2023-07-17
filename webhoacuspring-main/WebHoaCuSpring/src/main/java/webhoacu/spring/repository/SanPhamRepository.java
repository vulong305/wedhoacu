package webhoacu.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webhoacu.spring.model.SanPham;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
	//Custom query
	@Query(value = "SELECT * FROM sanpham sp WHERE sp.MaSP like %:keyword% or sp.TenSP like %:keyword% or sp.DacDiem like %:keyword% or sp.Anh like %:keyword% or sp.DonGia like %:keyword% or sp.GhiChu like %:keyword% or sp.MaLoaiSP like %:keyword% ", nativeQuery = true)
	List<SanPham> findByKeyword(@Param("keyword") String keyword);

	@Query(value = "SELECT * FROM sanpham sp WHERE sp.TenSP like %:keyword%", nativeQuery = true)
	List<SanPham> findByKeywordWithUserPage(@Param("keyword") String keyword);
	
	@Query(value = "SELECT * FROM sanpham sp WHERE sp.MaLoaiSP like %:idCategory%", nativeQuery = true)
	List<SanPham> findByCategoryID(@Param("idCategory") long idCategory);
	
	@Query(value = "SELECT * FROM sanpham sp WHERE sp.MaHangSX like %:idHangSanXuat%", nativeQuery = true)
	List<SanPham> findByHangSXID(@Param("idHangSanXuat") long idHangSanXuat);
	
	@Query(value = "SELECT * FROM sanpham sp WHERE :minPrice < sp.DonGia and sp.DonGia < :maxPrice", nativeQuery = true)
	List<SanPham> findByPrice(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice );

	@Query(value ="Select Sum(DonGia*SoLuong) From chitiethoadon", nativeQuery = true) 
	int tongDoanhThu();
	
	@Query(value ="Select Sum(DonGia*SoLuong) From chitiethoadon cthd JOIN hoadon hd on hd.MaHD = cthd.MaHD WHERE hd.NgayDat like %:date% ", nativeQuery = true) 
	String tongDoanhThu_Ngay(@Param("date") String date);
	
	@Query(value ="Select Sum(DonGia*SoLuong) From chitiethoadon cthd JOIN hoadon hd on hd.MaHD = cthd.MaHD WHERE hd.NgayDat like %:month% ", nativeQuery = true) 
	String tongDoanhThu_Thang(@Param("month") String month);

}
