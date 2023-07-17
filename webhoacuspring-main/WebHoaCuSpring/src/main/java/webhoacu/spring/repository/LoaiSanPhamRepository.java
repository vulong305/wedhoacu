package webhoacu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webhoacu.spring.model.LoaiSanPham;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Long> {
	//Custom query
	@Query(value = "SELECT * FROM loaisanpham lsp WHERE lsp.MaLoaiSP like %:keyword% or lsp.TenLoaiSP like %:keyword%", nativeQuery = true)
	List<LoaiSanPham> findByKeyword(@Param("keyword") String keyword);
	
	
}
