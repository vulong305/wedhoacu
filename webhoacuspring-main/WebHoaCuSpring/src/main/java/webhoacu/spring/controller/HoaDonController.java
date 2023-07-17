package webhoacu.spring.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webhoacu.spring.model.HoaDon;
import webhoacu.spring.model.KhachHang;
import webhoacu.spring.service.HoaDonService;
import webhoacu.spring.service.ChiTietHoaDonService;
import webhoacu.spring.service.KhachHangService;
import webhoacu.spring.service.SanPhamService;

@Controller
public class HoaDonController {

	@Autowired
	private HoaDonService HoaDonService;

	@Autowired
	private KhachHangService KhachHangService;

	@Autowired
	private ChiTietHoaDonService ChiTietHoaDonService;

	@Autowired
	private SanPhamService SanPhamService;

	@GetMapping("/403/HD")
	public String error403_HD() {
		return "admin/403";
	}

	@GetMapping("/page_hoadon")
	public String viewListHD(Model model) {
		model.addAttribute("listHoaDons", HoaDonService.getAllHoaDon());
		model.addAttribute("listKhachHangs", KhachHangService.getAllKhachHang());
		return findPaginatedHoaDon(1, "maHD", "asc", model);
		// "admin/page_hoadon";
	}

	@GetMapping("/showNewHoaDonForm")
	public String showNewHoaDonForm(Model model) {
		HoaDon hoadon = new HoaDon();
		model.addAttribute("hoadon", hoadon);
		model.addAttribute("listKhachHangs", KhachHangService.getAllKhachHang());
		return "admin/new_hoadon";
	}

	@PostMapping("/saveHoaDon")
	public String saveHD(@ModelAttribute("hoadon") HoaDon hoadon) {
		HoaDonService.saveHoaDon(hoadon);
		return "redirect:/page_hoadon";
	}

	@GetMapping("/showFormForDetailHD/{maHD}")
	public String showFormForDetailHD(@PathVariable(value = "maHD") long maHD, Model model) {
		model.addAttribute("listHoaDons", HoaDonService.getHoaDonById(maHD));
		model.addAttribute("listKhachHangs", KhachHangService.getAllKhachHang());
		model.addAttribute("listSanPhams", SanPhamService.getAllSanPham());
		return "admin/page_chitiethoadon";
	}

	@GetMapping("/showFormForUpdateHD/{maHD}")
	public String showFormForUpdateHD(@PathVariable(value = "maHD") long maHD, Model model) {
		HoaDon hoadon = HoaDonService.getHoaDonById(maHD);
		model.addAttribute("HoaDon", hoadon);
		model.addAttribute("listKhachHangs", KhachHangService.getAllKhachHang());
		return "admin/update_hoadon";
	}

	@GetMapping("/deleteHoaDon/{maHD}")
	public String deleteHoaDon(HttpServletRequest request, @PathVariable(value = "maHD") long maHD) {
		this.HoaDonService.deleteHoaDonById(maHD);
		return "redirect:" + request.getHeader("Referer");
	}

	/*
	 * HDông được trùng nên phải ghi search... để phân biệt (Nhớ ghi đúng bên html
	 * mới search được)
	 */
	@RequestMapping({ "/searchHD" })
	public String home(HoaDon HoaDon, Model model, String keyword) {
		if (keyword != null) {
			model.addAttribute("listHoaDons", HoaDonService.getByKeyword(keyword));
			model.addAttribute("listKhachHangs", KhachHangService.getAllKhachHang());
		} else {
			model.addAttribute("listHoaDons", HoaDonService.getAllHoaDon());
			model.addAttribute("listKhachHangs", KhachHangService.getAllKhachHang());
		}
		return "admin/page_hoadon";
	}

	@GetMapping("/pageHD/{pageNo}")
	public String findPaginatedHoaDon(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 3;

		Page<HoaDon> page = HoaDonService.findPaginatedHoaDon(pageNo, pageSize, sortField, sortDir);
		List<HoaDon> listHoaDons = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listHoaDons", listHoaDons);
		model.addAttribute("listKhachHangs", KhachHangService.getAllKhachHang());
		return "admin/page_hoadon";
	}
}
