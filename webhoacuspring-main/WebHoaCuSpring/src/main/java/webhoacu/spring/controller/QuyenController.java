package webhoacu.spring.controller;

import java.util.List;

import javax.validation.Valid;

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

import webhoacu.spring.model.Quyen;
import webhoacu.spring.service.QuyenService;

@Controller
public class QuyenController {

	@Autowired
	private QuyenService QuyenService;
	
	@GetMapping("/403/Q")
	public String error403_Quyen() {
		return "admin/403";
	}
	
	@GetMapping("/page_quyen")
	public String viewListQuyen(Model model) {
		model.addAttribute("listQuyens", QuyenService.getAllQuyen());
		return findPaginatedQuyen(1, "maQuyen", "asc", model);
	//		"admin/page_quyen";
	}
	
	@GetMapping("/showNewQuyenForm")
	public String showNewQuyenForm(Model model) {
		Quyen quyen = new Quyen();
	    model.addAttribute("quyen", quyen);
	    return "admin/new_quyen";
	 }
	
	@PostMapping("/saveQuyen")
	public String saveQuyen(@ModelAttribute("quyen") @Valid Quyen quyen) {
		QuyenService.saveQuyen(quyen);
		return "redirect:/page_quyen";
	 }
	
	@GetMapping("/showFormForUpdateQuyen/{maQuyen}")
	public String showFormForUpdateQuyen(@PathVariable ( value = "maQuyen") long maQuyen, Model model) {
		Quyen quyen = QuyenService.getQuyenById(maQuyen);
		model.addAttribute("quyen", quyen);
		return "admin/update_quyen";
	}
	
	@GetMapping("/deleteQuyen/{maQuyen}")
	public String deleteQuyen(@PathVariable (value = "maQuyen") long maQuyen) {
		this.QuyenService.deleteQuyenById(maQuyen);
		return "redirect:/page_quyen";
	}
	
	/* Không được trùng nên phải ghi search... để phân biệt (Nhớ ghi đúng bên html mới search được) */
	@RequestMapping({"/searchQuyen"})
	public String home(Quyen Quyen, Model model, String keyword) {
		if(keyword!=null) {
			model.addAttribute("listQuyens", QuyenService.getByKeyword(keyword));
		}
		else
		{
			model.addAttribute("listQuyens", QuyenService.getAllQuyen());}
		return "admin/page_quyen";
	 }
	
	@GetMapping("/pageQuyen/{pageNo}")
	public String findPaginatedQuyen(@PathVariable(value = "pageNo") int pageNo,
	    @RequestParam("sortField") String sortField,
	    @RequestParam("sortDir") String sortDir,
	    Model model) {
	    int pageSize = 3;

	    Page<Quyen> page = QuyenService.findPaginatedQuyen(pageNo, pageSize, sortField, sortDir);
	    List<Quyen> listQuyens = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());

	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	    model.addAttribute("listQuyens", listQuyens);
	    return "admin/page_quyen";
	}
}
