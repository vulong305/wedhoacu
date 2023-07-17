package webhoacu.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import webhoacu.spring.model.HoaDon;
import webhoacu.spring.repository.HoaDonRepository;

@Service
public class HoaDonService{
	@Autowired
	private HoaDonRepository HoaDonRepository;
	
	public long getLastIdHoaDon() {
		return HoaDonRepository.LastIdHoaDon();
	}
	
	public List<HoaDon> getAllHoaDon() {
		return HoaDonRepository.findAll();
	}

	public void saveHoaDon(HoaDon hoadon) {
		this.HoaDonRepository.save(hoadon);
		
	}


	public HoaDon getHoaDonById(long maHD) {
		Optional<HoaDon> optional = HoaDonRepository.findById(maHD);
		HoaDon hoadon = null;
		if (optional.isPresent()) {
			hoadon = optional.get();
		}
		else 
		{
			throw new RuntimeException(" Không tìm thấy hoá dơn với mã hoá đơn: " + maHD);    
		}
		return hoadon;
	}

	public void deleteHoaDonById(long maHD) {
		this.HoaDonRepository.deleteById(maHD);
	}


	public List<HoaDon> getByKeyword(String keyword) {
		return HoaDonRepository.findByKeyword(keyword);
	}
	
	// Phân trang
	public Page<HoaDon> findPaginatedHoaDon(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending(); 
			
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.HoaDonRepository.findAll(pageable);
		}
}

