package webhoacu.spring.controller;

import java.util.List;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import webhoacu.spring.model.NhanVien;
import webhoacu.spring.service.NhanVienService;
import webhoacu.spring.service.QuyenService;

@Controller
public class NhanVienController {
	
	@Autowired
	private NhanVienService NhanVienService;
	
	@Autowired 
	private QuyenService QuyenService;
	
	@GetMapping("/403")
	public String error403_NV() {
		return "admin/403";
	}
	
	@GetMapping("/page_nhanvien")
	public String viewListNV(Model model) {
		model.addAttribute("listNhanViens", NhanVienService.getAllNhanVien());
		model.addAttribute("listQuyens", QuyenService.getAllQuyen());
		return findPaginatedNhanVien(1, "maNV", "asc", model);
      //	"admin/page_nhanvien";
	}
	
	@GetMapping("/showNewNhanVienForm")
	public String showNewNhanVienForm(Model model) {
		NhanVien nhanvien = new NhanVien();
	    model.addAttribute("nhanvien",nhanvien);
	    model.addAttribute("listQuyens", QuyenService.getAllQuyen());
	    return "admin/new_nhanvien";
	 }
	
//	@PostMapping("/saveNhanVien")
//	public String saveNV(@ModelAttribute("nhanvien") NhanVien nhanvien) {
//		NhanVienService.saveNhanVien(nhanvien);
//		return "redirect:/page_nhanvien";
//	 }
	 
	@PostMapping("/saveNhanVien")
	public String saveNV(@ModelAttribute("nhanvien") @Valid NhanVien nhanvien, BindingResult bindingResult) {
		// Mã hoá mật khẩu lưu vào Database
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(nhanvien.getMatKhau());
        nhanvien.setMatKhau(encodedPassword);
        
		if (bindingResult.hasErrors()) 
		{
			return "admin/new_nhanvien";
		}
		else 
		{
			NhanVienService.saveNhanVien(nhanvien);
			return "redirect:/page_nhanvien";
		}
		
	 }
	
	@PostMapping("/updateNhanVien")
	public String updateNV(@ModelAttribute("nhanvien") @Valid NhanVien nhanvien, BindingResult bindingResult) {
		// Mã hoá mật khẩu lưu vào Database
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(nhanvien.getMatKhau());
        nhanvien.setMatKhau(encodedPassword);
        
		if (bindingResult.hasErrors()) 
		{
			return "admin/update_nhanvien";
		}
		else 
		{
			NhanVienService.saveNhanVien(nhanvien);
			return "redirect:/page_nhanvien";
		}
		
	 }
	
	@GetMapping("/showFormForUpdateNV/{maNV}")
	public String showFormForUpdateNV(@PathVariable ( value = "maNV") long maNV, Model model) {
		NhanVien nhanvien = NhanVienService.getNhanVienById(maNV);
		model.addAttribute("nhanvien", nhanvien);
		model.addAttribute("listQuyens", QuyenService.getAllQuyen());
		return "admin/update_nhanvien";
	}
	
	@GetMapping("/deleteNhanVien/{maNV}")
	public String deleteNhanVien(@PathVariable (value = "maNV") long maNV) {
		this.NhanVienService.deleteNhanVienById(maNV);
		return "redirect:/page_nhanvien";
	}
	
	/* NVông được trùng nên phải ghi search... để phân biệt (Nhớ ghi đúng bên html mới search được) */
	@RequestMapping({"/searchNV"})
	public String home(NhanVien NhanVien, Model model, String keyword) {
		if(keyword!=null) {
			model.addAttribute("listNhanViens", NhanVienService.getByKeyword(keyword));
		}
		else
		{
			model.addAttribute("listNhanViens", NhanVienService.getAllNhanVien());}
		return "admin/page_nhanvien";
	 }
	
	@GetMapping("/pageNV/{pageNo}")
	public String findPaginatedNhanVien(@PathVariable(value = "pageNo") int pageNo,
	    @RequestParam("sortField") String sortField,
	    @RequestParam("sortDir") String sortDir,
	    Model model) {
	    int pageSize = 3;

	    Page<NhanVien> page = NhanVienService.findPaginatedNhaCungCap(pageNo, pageSize, sortField, sortDir);
	    List<NhanVien> listNhanViens = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());

	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	    model.addAttribute("listNhanViens", listNhanViens);
	    return "admin/page_nhanvien";
	}
}
