package webhoacu.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import webhoacu.spring.model.Quyen;
import webhoacu.spring.repository.QuyenRepository;

@Service
public class QuyenService{

	@Autowired
	private QuyenRepository QuyenRepository;
	

	public List<Quyen> getAllQuyen() {
		return QuyenRepository.findAll();
	}

	public void saveQuyen(Quyen quyen) {
		this.QuyenRepository.save(quyen);
	}


	public Quyen getQuyenById(long maQuyen) {
		Optional<Quyen> optional = QuyenRepository.findById(maQuyen);
		Quyen quyen = null;
		if (optional.isPresent()) {
			quyen = optional.get();
		}
		else 
		{
			throw new RuntimeException(" Không tìm thấy quyền với mã quyền: " + maQuyen);    
		}
		return quyen;
	}

	public void deleteQuyenById(long maQuyen) {
		this.QuyenRepository.deleteById(maQuyen);
	}

	public List<Quyen> getByKeyword(String keyword) {
		return QuyenRepository.findByKeyword(keyword);
	}

	// Phân trang
	public Page<Quyen> findPaginatedQuyen(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending(); 
			
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.QuyenRepository.findAll(pageable);
	}
}
