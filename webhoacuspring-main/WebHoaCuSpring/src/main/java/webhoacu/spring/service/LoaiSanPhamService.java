package webhoacu.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import webhoacu.spring.model.LoaiSanPham;
import webhoacu.spring.repository.LoaiSanPhamRepository;

@Service
public class LoaiSanPhamService{

	
	@Autowired
	private LoaiSanPhamRepository LoaiSanPhamRepository;

	public List<LoaiSanPham> getAllLoaiSanPham() {
		return LoaiSanPhamRepository.findAll();
	}

	public void saveLoaiSanPham(LoaiSanPham loaisanpham) {
		this.LoaiSanPhamRepository.save(loaisanpham);
		
	}


	public LoaiSanPham getLoaiSanPhamById(long maLoaiSP) {
		Optional<LoaiSanPham> optional = LoaiSanPhamRepository.findById(maLoaiSP);
		LoaiSanPham loaisanpham = null;
		if (optional.isPresent()) {
			loaisanpham = optional.get();
		}
		else 
		{
			throw new RuntimeException(" Không tìm thấy loại sản phẩm với mã loại sản phẩm: " + maLoaiSP);    
		}
		return loaisanpham;
	}


	public void deleteLoaiSanPhamById(long maLoaiSP) {
		this.LoaiSanPhamRepository.deleteById(maLoaiSP);
	}

	public List<LoaiSanPham> getByKeyword(String keyword) {
		return LoaiSanPhamRepository.findByKeyword(keyword);
	}
	
	// Phân trang
	public Page<LoaiSanPham> findPaginatedLoaiSanPham(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending(); 
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.LoaiSanPhamRepository.findAll(pageable);
	}

}
