package webhoacu.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webhoacu.spring.model.ChiTietHoaDon;
import webhoacu.spring.repository.ChiTietHoaDonRepository;

@Service
public class ChiTietHoaDonService{
	@Autowired
	private ChiTietHoaDonRepository ChiTietHoaDonRepository;
	

	public List<ChiTietHoaDon> getAllChiTietHoaDon() {
		return ChiTietHoaDonRepository.findAll();
	}

	public List<ChiTietHoaDon> getChiTietHDByMaHD(long maHD){
		return ChiTietHoaDonRepository.fintByMaHD(maHD);
	}

	public void addChiTietHoaDonUseQuery(ChiTietHoaDon cthd) {
		ChiTietHoaDonRepository.addCTHD(cthd.getMaHD(), cthd.getMaSP(), cthd.getSoLuong(), cthd.getDonGia());
	}
	

}
