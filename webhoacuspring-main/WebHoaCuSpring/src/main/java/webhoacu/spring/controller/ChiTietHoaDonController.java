package webhoacu.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import webhoacu.spring.service.ChiTietHoaDonService;
import webhoacu.spring.service.HoaDonService;
import webhoacu.spring.service.SanPhamService;

@Controller
public class ChiTietHoaDonController {

	@Autowired
	private ChiTietHoaDonService ChiTietHoaDonService;
	
	@Autowired
	private HoaDonService HoaDonService;
	
	@Autowired
	private SanPhamService SanPhamService;
	
	@GetMapping("/page_chitiethoadon/{maHD}")
	public String viewListCTHD(Model model,@PathVariable ( value = "maHD") long maHD) {
		model.addAttribute("listChiTietHoaDons", ChiTietHoaDonService.getChiTietHDByMaHD(maHD));
		String soHoaDon = HoaDonService.getHoaDonById(maHD).getShd();
		model.addAttribute("soHD",soHoaDon);
		model.addAttribute("listHoaDons", HoaDonService.getAllHoaDon());
		model.addAttribute("listSanPhams", SanPhamService.getAllSanPham());
		return "admin/page_chitiethoadon";
	}
}
