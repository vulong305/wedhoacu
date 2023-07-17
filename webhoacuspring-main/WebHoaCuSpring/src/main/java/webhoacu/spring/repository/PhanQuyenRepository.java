package webhoacu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webhoacu.spring.model.PhanQuyen;
import webhoacu.spring.model.Quyen;

@Repository
public interface PhanQuyenRepository extends JpaRepository<PhanQuyen, Long>{
	//Custom query
	@Query(value = "SELECT * FROM nhanvien_quyen nvq JOIN nhanvien nv on nvq.nhanvien_id = nv.maNV JOIN Quyen q on nvq.quyen_id = q.maQuyen WHERE nv.hoTen like %:keyword% or q.tenQuyen like %:keyword%", nativeQuery = true)
	List<PhanQuyen> findByKeyword(@Param("keyword") String keyword);

}
