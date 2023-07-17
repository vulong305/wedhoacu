package webhoacu.spring.controller;

import java.util.List;

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

import webhoacu.spring.model.PhanQuyen;
import webhoacu.spring.service.NhanVienService;
import webhoacu.spring.service.QuyenService;
import webhoacu.spring.service.PhanQuyenService;

@Controller
public class PhanQuyenController {
	@Autowired
	private PhanQuyenService PhanQuyenService;
	
	@Autowired
	private NhanVienService NhanVienService;

	@Autowired
	private QuyenService QuyenService;
	
	@GetMapping("/403/PQ")
	public String error403_PQ() {
		return "admin/403";
	}
	
	@GetMapping("/page_phanquyen")
	public String viewListQuyen(Model model) {
		model.addAttribute("listPhanQuyens", PhanQuyenService.getAllPhanQuyen());
		model.addAttribute("listNhanViens", NhanVienService.getAllNhanVien());
		model.addAttribute("listQuyens", QuyenService.getAllQuyen());
		return findPaginatedPhanQuyen(1, "maNV", "asc", model);
	//		"admin/page_phanquyen";
	}
	
	@GetMapping("/showNewPhanQuyenForm")
	public String showNewQuyenForm(Model model) {
		PhanQuyen phanquyen = new PhanQuyen();
	    model.addAttribute("phanquyen", phanquyen);
	    model.addAttribute("listNhanViens", NhanVienService.getAllNhanVien());
		model.addAttribute("listQuyens", QuyenService.getAllQuyen());
	    return "admin/new_phanquyen";
	 }
	
	@PostMapping("/savePhanQuyen")
	public String saveQuyen(@ModelAttribute("quyen") PhanQuyen phanquyen) {
		PhanQuyenService.savePhanQuyen(phanquyen);
		return "redirect:/page_phanquyen";
	 }
	
	@GetMapping("/showFormForUpdatePhanQuyen/{maNV}")
	public String showFormForUpdateQuyen(@PathVariable ( value = "maNV") long maNV, Model model) {
		PhanQuyen phanquyen = PhanQuyenService.getPhanQuyenById(maNV);
		model.addAttribute("listNhanViens", NhanVienService.getAllNhanVien());
		model.addAttribute("listQuyens", QuyenService.getAllQuyen());
		model.addAttribute("phanquyen", phanquyen);
		return "admin/update_phanquyen";
	}
	
	@GetMapping("/deletePhanQuyen/{maNV}")
	public String deletePhanQuyen(@PathVariable (value = "maNV") long maNV) {
		this.PhanQuyenService.deletePhanQuyenById(maNV);
		return "redirect:/page_phanquyen";
	}
	
	/* Không được trùng nên phải ghi search... để phân biệt (Nhớ ghi đúng bên html mới search được) */
	@RequestMapping({"/searchPhanQuyen"})
	public String home(PhanQuyen PhanQuyen, Model model, String keyword) {
		if(keyword!=null) {
			model.addAttribute("listPhanQuyens", PhanQuyenService.getByKeyword(keyword));
			model.addAttribute("listNhanViens", NhanVienService.getAllNhanVien());
			model.addAttribute("listQuyens", QuyenService.getAllQuyen());
		}
		else
		{
			model.addAttribute("listPhanQuyens", PhanQuyenService.getAllPhanQuyen());}
			model.addAttribute("listNhanViens", NhanVienService.getAllNhanVien());
			model.addAttribute("listQuyens", QuyenService.getAllQuyen());
		return "admin/page_phanquyen";
	 }
	
	@GetMapping("/pagePhanQuyen/{pageNo}")
	public String findPaginatedPhanQuyen(@PathVariable(value = "pageNo") int pageNo,
	    @RequestParam("sortField") String sortField,
	    @RequestParam("sortDir") String sortDir,
	    Model model) {
	    int pageSize = 2;

	    Page<PhanQuyen> page = PhanQuyenService.findPaginatedPhanQuyen(pageNo, pageSize, sortField, sortDir);
	    List<PhanQuyen> listPhanQuyens = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());

	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	    model.addAttribute("listPhanQuyens", listPhanQuyens);
		model.addAttribute("listNhanViens", NhanVienService.getAllNhanVien());
		model.addAttribute("listQuyens", QuyenService.getAllQuyen());
	    return "admin/page_phanquyen";
	}
}
