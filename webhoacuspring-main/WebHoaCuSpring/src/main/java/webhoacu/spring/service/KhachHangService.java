package webhoacu.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import webhoacu.spring.model.KhachHang;
import webhoacu.spring.repository.KhachHangRepository;

@Service
public class KhachHangService{

	@Autowired
	private KhachHangRepository KhachHangRepository;
	
	public long getLastIdKhachHang(){
		return KhachHangRepository.LastIdKhachHang();
	}

	public List<KhachHang> getAllKhachHang() {
		return KhachHangRepository.findAll();
	}

	public void saveKhachHang(KhachHang khachhang) {
		this.KhachHangRepository.save(khachhang);
		
	}

	public KhachHang getKhachHangById(long maKH) {
		Optional<KhachHang> optional = KhachHangRepository.findById(maKH);
		KhachHang khachhang = null;
		if (optional.isPresent()) {
			khachhang = optional.get();
		}
		else 
		{
			throw new RuntimeException(" Không tìm thấy khách hàng với mã khách hàng: " + maKH);    
		}
		return khachhang;
	}

	public void deleteKhachHangById(long maKH) {
		this.KhachHangRepository.deleteById(maKH);
	}

	public List<KhachHang> getByKeyword(String keyword) {
		return KhachHangRepository.findByKeyword(keyword);
	}

	// Phân trang
	public Page<KhachHang> findPaginatedNhaCungCap(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending(); 
			
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.KhachHangRepository.findAll(pageable);
		}
}