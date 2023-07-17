package webhoacu.spring.controller;

import java.util.List;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webhoacu.spring.model.KhachHang;
import webhoacu.spring.model.LoaiSanPham;
import webhoacu.spring.model.NhanVien;
import webhoacu.spring.model.SanPham;
import webhoacu.spring.service.LoaiSanPhamService;

@Controller
public class LoaiSanPhamController {

	@Autowired
	private LoaiSanPhamService LoaiSanPhamService;
	
	@GetMapping("/403/LSP")
	public String error403_LSP() {
		return "admin/403";
	}
	
	@GetMapping("/page_loaisanpham")
	public String viewListLSP(Model model) {
		model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
		return findPaginatedLoaiSanPham(1, "maLoaiSP", "asc", model);
	//  	"admin/page_loaisanpham";
	}
	
	@GetMapping("/showNewLoaiSanPhamForm")
	public String showNewLoaiSanPhamForm(Model model) {
		LoaiSanPham loaisanpham = new LoaiSanPham();
	    model.addAttribute("loaisanpham", loaisanpham);
	    return "admin/new_loaisanpham";
	 }
	
//	@PostMapping("/saveLoaiSanPham")
//	public String saveLSP(@ModelAttribute("loaisanpham") LoaiSanPham loaisanpham) {
//		LoaiSanPhamService.saveLoaiSanPham(loaisanpham);
//		return "redirect:/page_loaisanpham";
//	 }
	
	@PostMapping("/saveLoaiSanPham")
	public String saveLSP(@ModelAttribute("loaisanpham") @Valid  LoaiSanPham loaisanpham, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) 
		{
			return "admin/new_loaisanpham";
		}
		else 
		{
			LoaiSanPhamService.saveLoaiSanPham(loaisanpham);
			return "redirect:/page_loaisanpham";
		}
		
	 }
	
	@PostMapping("/updateLoaiSanPham")
	public String updateLSP(@ModelAttribute("loaisanpham") @Valid LoaiSanPham loaisanpham, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) 
		{
			return "admin/update_loaisanpham";
		}
		else 
		{
			LoaiSanPhamService.saveLoaiSanPham(loaisanpham);
			return "redirect:/page_loaisanpham";
		}
		
	 }
	
	
	@GetMapping("/showFormForUpdateLSP/{maLoaiSP}")
	public String showFormForUpdateLSP(@PathVariable ( value = "maLoaiSP") long maLoaiSP, Model model) {
		LoaiSanPham loaisanpham = LoaiSanPhamService.getLoaiSanPhamById(maLoaiSP);
		model.addAttribute("loaisanpham", loaisanpham);
		return "admin/update_loaisanpham";
	}
	
	@GetMapping("/deleteLoaiSanPham/{maLoaiSP}")
	public String deleteLoaiSanPham(@PathVariable (value = "maLoaiSP") long maLoaiSP) {
		this.LoaiSanPhamService.deleteLoaiSanPhamById(maLoaiSP);
		return "redirect:/page_loaisanpham";
	}
	
	/* Không được trùng nên phải ghi search... để phân biệt (Nhớ ghi đúng bên html mới search được) */
	@RequestMapping({"/searchLSP"})
	public String home(LoaiSanPham LoaiSanPham, Model model, String keyword) {
		if(keyword!=null) {
			model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getByKeyword(keyword));
		}
		else
		{
			model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());}
		return "admin/page_loaisanpham";
	 }
	 
	@GetMapping("/pageLSP/{pageNo}")
	public String findPaginatedLoaiSanPham(@PathVariable(value = "pageNo") int pageNo,
	    @RequestParam("sortField") String sortField,
	    @RequestParam("sortDir") String sortDir,
	    Model model) {
	    int pageSize = 3;

	    Page<LoaiSanPham> page = LoaiSanPhamService.findPaginatedLoaiSanPham(pageNo, pageSize, sortField, sortDir);
	    List<LoaiSanPham> listLoaiSanPhams = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());

	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	    model.addAttribute("listLoaiSanPhams", listLoaiSanPhams);
	    return "admin/page_loaisanpham";
	}
}
