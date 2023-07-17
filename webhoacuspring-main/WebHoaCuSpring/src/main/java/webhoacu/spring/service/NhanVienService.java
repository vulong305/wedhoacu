package webhoacu.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import webhoacu.spring.model.NhanVien;
import webhoacu.spring.repository.NhanVienRepository;

@Service
public class NhanVienService{
	
	@Autowired
	private NhanVienRepository NhanVienRepository;

	public List<NhanVien> getAllNhanVien() {
		return NhanVienRepository.findAll();
	}

	public void saveNhanVien(NhanVien nhanvien) {
		this.NhanVienRepository.save(nhanvien);
		
	}

	public NhanVien getNhanVienById(long maNV) {
		Optional<NhanVien> optional = NhanVienRepository.findById(maNV);
		NhanVien nhanvien = null;
		if (optional.isPresent()) {
			nhanvien = optional.get();
		}
		else 
		{
			throw new RuntimeException(" Không tìm thấy nhân viên với mã nhân viên: " + maNV);    
		}
		return nhanvien;
	}


	public void deleteNhanVienById(long maNV) {
		this.NhanVienRepository.deleteById(maNV);
	}

	public List<NhanVien> getByKeyword(String keyword) {
		return NhanVienRepository.findByKeyword(keyword);
	}
	
	// Phân trang
		public Page<NhanVien> findPaginatedNhaCungCap(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending(); 
			
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.NhanVienRepository.findAll(pageable);
		}
}
