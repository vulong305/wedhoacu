package webhoacu.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import webhoacu.spring.model.HangSanXuat;
import webhoacu.spring.repository.HangSanXuatRepository;

@Service
public class HangSanXuatService{

	
	@Autowired
	private HangSanXuatRepository HangSanXuatRepository;

	public List<HangSanXuat> getAllHangSanXuat() {
		return HangSanXuatRepository.findAll();
	}

	public void saveHangSanXuat(HangSanXuat loaisanpham) {
		this.HangSanXuatRepository.save(loaisanpham);
		
	}


	public HangSanXuat getHangSanXuatById(long maHangSX) {
		Optional<HangSanXuat> optional = HangSanXuatRepository.findById(maHangSX);
		HangSanXuat hangsanxuat = null;
		if (optional.isPresent()) {
			hangsanxuat = optional.get();
		}
		else 
		{
			throw new RuntimeException(" Không tìm thấy loại sản phẩm với mã loại sản phẩm: " + maHangSX);    
		}
		return hangsanxuat;
	}


	public void deleteHangSanXuatById(long maHangSX) {
		this.HangSanXuatRepository.deleteById(maHangSX);
	}

	public List<HangSanXuat> getByKeyword(String keyword) {
		return HangSanXuatRepository.findByKeyword(keyword);
	}
	
	// Phân trang
	public Page<HangSanXuat> findPaginatedHangSanXuat(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending(); 
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.HangSanXuatRepository.findAll(pageable);
	}

}
