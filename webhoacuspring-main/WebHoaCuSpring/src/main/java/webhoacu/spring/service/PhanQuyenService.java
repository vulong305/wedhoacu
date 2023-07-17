package webhoacu.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import webhoacu.spring.model.PhanQuyen;
import webhoacu.spring.model.Quyen;
import webhoacu.spring.repository.PhanQuyenRepository;


@Service
public class PhanQuyenService {

	@Autowired
	private PhanQuyenRepository PhanQuyenRepository;
	

	public List<PhanQuyen> getAllPhanQuyen() {
		return PhanQuyenRepository.findAll();
	}

	public void savePhanQuyen(PhanQuyen phanquyen) {
		this.PhanQuyenRepository.save(phanquyen);
	}


	public PhanQuyen getPhanQuyenById(long maNV) {
		Optional<PhanQuyen> optional = PhanQuyenRepository.findById(maNV);
		PhanQuyen phanquyen = null;
		if (optional.isPresent()) {
			phanquyen = optional.get();
		}
		else 
		{
			throw new RuntimeException(" Không tìm thấy ");    
		}
		return phanquyen;
	}

	public void deletePhanQuyenById(long maNV) {
		this.PhanQuyenRepository.deleteById(maNV);
	}

	public List<PhanQuyen> getByKeyword(String keyword) {
		return PhanQuyenRepository.findByKeyword(keyword);
	}

	// Phân trang
	public Page<PhanQuyen> findPaginatedPhanQuyen(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending(); 
			
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.PhanQuyenRepository.findAll(pageable);
	}
}
