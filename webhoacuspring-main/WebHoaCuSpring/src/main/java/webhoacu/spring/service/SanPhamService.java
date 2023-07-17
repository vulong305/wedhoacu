package webhoacu.spring.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import webhoacu.spring.model.SanPham;
import webhoacu.spring.repository.SanPhamRepository;

@Service
public class SanPhamService {
	
	@Autowired
	private SanPhamRepository SanPhamRepository;

	public List<SanPham> getAllSanPham() {
		return SanPhamRepository.findAll();
	}

	public void saveSanPham(SanPham sanpham) {
		this.SanPhamRepository.save(sanpham);
		
	}

	public SanPham getSanPhamById(long maSP) {
		Optional<SanPham> optional = SanPhamRepository.findById(maSP);
		SanPham sanpham = null;
		if (optional.isPresent()) {
			sanpham = optional.get();
		}
		else 
		{
			throw new RuntimeException(" Không tìm thấy sản phẩm với mã sản phẩm: " + maSP);    
		}
		return sanpham;
	}

	public void deleteSanPhamById(long maSP) {
		this.SanPhamRepository.deleteById(maSP);
	}

	public List<SanPham> getByKeyword(String keyword) {
		return SanPhamRepository.findByKeyword(keyword);
	}
	
	public List<SanPham> getByCategoryID(long idCategory){
		return SanPhamRepository.findByCategoryID(idCategory);
	}
	
	public List<SanPham> getByHangSXID(long idHangSX){
		return SanPhamRepository.findByHangSXID(idHangSX);
	}
	
	public List<SanPham> getByKeywordWithUserPage(String keyword) {
		return SanPhamRepository.findByKeywordWithUserPage(keyword);
	}
	
	// Tính tổng doanh thu
	public int tongDoanhThu() {
		return SanPhamRepository.tongDoanhThu();
	}
	
	// Tính tổng doanh thu theo ngày
	public String tongDoanhThu_Ngay(String date) {
		return SanPhamRepository.tongDoanhThu_Ngay(date);
	}
	
	// Tính tổng doanh thu theo tháng
	public String tongDoanhThu_Thang(String month) {
		return SanPhamRepository.tongDoanhThu_Thang(month);
	}
	

	//Tìm theo giá cả
	public List<SanPham> getByMinMaxPrice(int minPrice, int maxPrice){
		return SanPhamRepository.findByPrice(minPrice, maxPrice);
	}
	
	//Phân trang
	public Page<SanPham> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.SanPhamRepository.findAll(pageable);
	}
	
	public Page<SanPham> findPaginatedSanPham(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending(); 
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.SanPhamRepository.findAll(pageable);
	}

}
