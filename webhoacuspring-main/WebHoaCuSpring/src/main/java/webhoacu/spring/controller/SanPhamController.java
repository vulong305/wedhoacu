package webhoacu.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import webhoacu.spring.model.FileUploadUtil;
import webhoacu.spring.model.LoaiSanPham;
import webhoacu.spring.model.MyUserDetails;
import webhoacu.spring.model.NhanVien;
import webhoacu.spring.model.SanPham;
import webhoacu.spring.repository.SanPhamRepository;
import webhoacu.spring.service.HangSanXuatService;
import webhoacu.spring.service.LoaiSanPhamService;
import webhoacu.spring.service.SanPhamService;


@Controller
public class SanPhamController {

	@Autowired
	private SanPhamService SanPhamService;

	@Autowired
	private LoaiSanPhamService LoaiSanPhamService;

	@Autowired
	private HangSanXuatService hangSanXuatService;


	@GetMapping("/403/SP")
	public String error403_SP() {
		return "admin/403";
	}
	
//	@GetMapping("/page_sanpham")
//	public String viewListSP(@AuthenticationPrincipal MyUserDetails user, Model model) {
//		model.addAttribute("user", user);
//		model.addAttribute("listSanPhams", SanPhamService.getAllSanPham());
//		model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
//		return findPaginatedSanPham(1, "maSP", "asc", model);
//		// "admin/page_sanpham";
//	}

	@GetMapping("/page_sanpham")
	public String viewListSP( Model model) {
		model.addAttribute("listSanPhams", SanPhamService.getAllSanPham());
		model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
		model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
		return findPaginatedSanPham(1, "maSP", "asc", model);
		// "admin/page_sanpham";
	}
	
	@GetMapping("/showNewSanPhamForm")
	public String showNewSanPhamForm(Model model) {
		SanPham sanpham = new SanPham();
		model.addAttribute("sanpham", sanpham);
		model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
		model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
		return "admin/new_sanpham";
	}

	// @PostMapping("/saveSanPham")
	// public String saveSP(@ModelAttribute("sanpham") SanPham sanpham) {
	// SanPhamService.saveSanPham(sanpham);
	// return "redirect:/page_sanpham";
	// }
	@PostMapping("/saveSanPham")
    public String saveSP(@ModelAttribute("sanpham") @Valid  SanPham sanpham, BindingResult bindingResult,
    		Model model,
    		@RequestParam("image") MultipartFile multipartFile) throws IOException {
        
		//kiểm tra có chọn file hay không 
		int size = 0;
		if (multipartFile != null && !multipartFile.isEmpty()) size++;
		
		//Nếu có thì cập nhập, không thì lấy lại giá trị ảnh của SanPham truyền vào 
		if (size != 0) {
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        String uploadDir = "src/main/resources/static/image";
	        String uploadDir1 = "target/classes/static/image";
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	        FileUploadUtil.saveFile(uploadDir1, fileName, multipartFile);
	        sanpham.setAnh(fileName);
		}
	
        // Up vào cả 2 folder, để cả 2 hoặc để resources folder sẽ báo lỗi nhưng vẫn được nếu F5 lại  
//      String uploadDir = "src/main/resources/static/images";
        
 
//        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
 
        if (bindingResult.hasErrors()) 
		{
        	model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
        	model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
			return "admin/new_sanpham";
			
		}
		else 
		{
			SanPhamService.saveSanPham(sanpham);
			return "redirect:/page_sanpham";
		}
    }
	
	@PostMapping("/updateSanPham")
    public String updateSP(@ModelAttribute("sanpham") @Valid  SanPham sanpham, BindingResult bindingResult,
    		Model model,
    		@RequestParam("image") MultipartFile multipartFile) throws IOException {
        
		//kiểm tra có chọn file hay không 
		int size = 0;
		if (multipartFile != null && !multipartFile.isEmpty()) size++;
		
		//Nếu có thì cập nhập, không thì lấy lại giá trị ảnh của SanPham truyền vào 
		if (size != 0) {
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        String uploadDir = "src/main/resources/static/image";
	        String uploadDir1 = "target/classes/static/image";
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	        FileUploadUtil.saveFile(uploadDir1, fileName, multipartFile);
	        sanpham.setAnh(fileName);
		}
        // Up vào cả 2 folder, để cả 2 hoặc để resources folder sẽ báo lỗi nhưng vẫn được nếu F5 lại  
//      String uploadDir = "src/main/resources/static/images";
        
 
//        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
 
        if (bindingResult.hasErrors()) 
		{
        	model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
        	model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
			return "admin/update_sanpham";
			
		}
		else 
		{
			SanPhamService.saveSanPham(sanpham);
			return "redirect:/page_sanpham";
		}
    }
	
	
	@GetMapping("/showFormForDetailSP/{maSP}")
	public String showFormForDetailSP(@PathVariable(value = "maSP") long maSP, Model model) {
		SanPham sanpham = SanPhamService.getSanPhamById(maSP);
		model.addAttribute("sanpham", sanpham);
		model.addAttribute("listSanPhams", SanPhamService.getAllSanPham());
		model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
		model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
		return "admin/detail_sanpham";
	}

	@GetMapping("/showFormForUpdateSP/{maSP}")
	public String showFormForUpdateSP(@PathVariable(value = "maSP") long maSP, Model model) {
		SanPham sanpham = SanPhamService.getSanPhamById(maSP);
		model.addAttribute("sanpham", sanpham);
		model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
		model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
		return "admin/update_sanpham";
	}

	@GetMapping("/deleteSanPham/{maSP}")
	public String deleteSanPham(@PathVariable(value = "maSP") long maSP) {
		this.SanPhamService.deleteSanPhamById(maSP);
		return "redirect:/page_sanpham";
	}

	/*
	 * Không được trùng nên phải ghi search... để phân biệt (Nhớ ghi đúng bên html
	 * mới search được)
	 */
	@RequestMapping({ "/searchSP" })
	public String home(SanPham SanPham, Model model, String keyword) {
		if (keyword != null) {
			model.addAttribute("listSanPhams", SanPhamService.getByKeyword(keyword));
			model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
			model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
		} else {
			model.addAttribute("listSanPhams", SanPhamService.getAllSanPham());
			model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
			model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
		}
		return "admin/page_sanpham";
	}

	@GetMapping("/pageSP/{pageNo}")
	public String findPaginatedSanPham(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 3;

		Page<SanPham> page = SanPhamService.findPaginatedSanPham(pageNo, pageSize, sortField, sortDir);
		List<SanPham> listSanPhams = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listSanPhams", listSanPhams);
		model.addAttribute("listLoaiSanPhams", LoaiSanPhamService.getAllLoaiSanPham());
		model.addAttribute("listHangSanXuats", hangSanXuatService.getAllHangSanXuat());
		return "admin/page_sanpham";
	}

}
