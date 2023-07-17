package webhoacu.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webhoacu.spring.model.HangSanXuat;
import webhoacu.spring.service.HangSanXuatService;

@Controller
public class HangSanXuatController {

	
	@Autowired
	private HangSanXuatService HangSanXuatService;
	
	@GetMapping("/403/HSX")
	public String error403_HSX() {
		return "admin/403";
	}
	
	@GetMapping("/page_hangsanxuat")
	public String viewListHSX(Model model) {
		model.addAttribute("listHangSanXuats", HangSanXuatService.getAllHangSanXuat());
		return findPaginatedHangSanXuat(1, "maHangSX", "asc", model);
	//  	"admin/page_HangSanXuat";
	}
	
	@GetMapping("/showNewHangSanXuatForm")
	public String showNewHangSanXuatForm(Model model) {
		HangSanXuat HangSanXuat = new HangSanXuat();
	    model.addAttribute("HangSanXuat", HangSanXuat);
	    return "admin/new_hangsanxuat";
	 }
	
//	@PostMapping("/saveHangSanXuat")
//	public String saveHSX(@ModelAttribute("HangSanXuat") HangSanXuat HangSanXuat) {
//		HangSanXuatService.saveHangSanXuat(HangSanXuat);
//		return "redirect:/page_HangSanXuat";
//	 }
	
	@PostMapping("/saveHangSanXuat")
	public String saveHSX(@ModelAttribute("HangSanXuat") @Valid  HangSanXuat HangSanXuat, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) 
		{
			return "admin/new_hangsanxuat";
		}
		else 
		{
			HangSanXuatService.saveHangSanXuat(HangSanXuat);
			return "redirect:/page_hangsanxuat";
		}
		
	 }
	
	@PostMapping("/updateHangSanXuat")
	public String updateHSX(@ModelAttribute("HangSanXuat") @Valid  HangSanXuat HangSanXuat, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) 
		{
			return "admin/update_hangsanxuat";
		}
		else 
		{
			HangSanXuatService.saveHangSanXuat(HangSanXuat);
			return "redirect:/page_hangsanxuat";
		}
		
	 }
	
	
	@GetMapping("/showFormForUpdateHSX/{maHangSX}")
	public String showFormForUpdateHSX(@PathVariable ( value = "maHangSX") long maHangSX, Model model) {
		HangSanXuat HangSanXuat = HangSanXuatService.getHangSanXuatById(maHangSX);
		model.addAttribute("HangSanXuat", HangSanXuat);
		return "admin/update_hangsanxuat";
	}
	
	@GetMapping("/deleteHangSanXuat/{maHangSX}")
	public String deleteHangSanXuat(@PathVariable (value = "maHangSX") long maHangSX) {
		this.HangSanXuatService.deleteHangSanXuatById(maHangSX);
		return "redirect:/page_hangsanxuat";
	}
	
	/* Không được trùng nên phải ghi search... để phân biệt (Nhớ ghi đúng bên html mới search được) */
	@RequestMapping({"/searchHSX"})
	public String home(HangSanXuat HangSanXuat, Model model, String keyword) {
		if(keyword!=null) {
			model.addAttribute("listHangSanXuats", HangSanXuatService.getByKeyword(keyword));
		}
		else
		{
			model.addAttribute("listHangSanXuats", HangSanXuatService.getAllHangSanXuat());}
		return "admin/page_hangsanxuat";
	 }
	 
	@GetMapping("/pageHSX/{pageNo}")
	public String findPaginatedHangSanXuat(@PathVariable(value = "pageNo") int pageNo,
	    @RequestParam("sortField") String sortField,
	    @RequestParam("sortDir") String sortDir,
	    Model model) {
	    int pageSize = 3;

	    Page<HangSanXuat> page = HangSanXuatService.findPaginatedHangSanXuat(pageNo, pageSize, sortField, sortDir);
	    List<HangSanXuat> listHangSanXuats = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());

	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	    model.addAttribute("listHangSanXuats", listHangSanXuats);
	    return "admin/page_hangsanxuat";
	}
}
