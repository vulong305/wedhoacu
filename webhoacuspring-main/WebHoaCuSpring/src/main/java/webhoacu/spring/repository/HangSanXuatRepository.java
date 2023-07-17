package webhoacu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webhoacu.spring.model.HangSanXuat;

@Repository
public interface HangSanXuatRepository extends JpaRepository<HangSanXuat, Long> {
	//Custom query
	@Query(value = "SELECT * FROM hangsanxuat hsx WHERE hsx.MaHangSX like %:keyword% or hsx.TenHangSX like %:keyword%", nativeQuery = true)
	List<HangSanXuat> findByKeyword(@Param("keyword") String keyword);
	
	
}
