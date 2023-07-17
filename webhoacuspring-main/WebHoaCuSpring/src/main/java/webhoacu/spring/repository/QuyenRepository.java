package webhoacu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webhoacu.spring.model.Quyen;

@Repository
public interface QuyenRepository extends JpaRepository<Quyen, Long>, CrudRepository<Quyen, Long> {
	//Custom query
	@Query(value = "SELECT * FROM quyen q WHERE q.MaQuyen like %:keyword% or q.TenQuyen like %:keyword%", nativeQuery = true)
	List<Quyen> findByKeyword(@Param("keyword") String keyword);
	
	Quyen findByTenQuyen(String tenQuyen);

}
