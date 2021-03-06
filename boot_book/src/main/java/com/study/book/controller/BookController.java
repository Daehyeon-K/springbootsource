package com.study.book.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.book.dto.BookDTO;
import com.study.book.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	private BookService service;
	
	// ? μ²? λ¦¬μ€?Έ λ³΄μ¬μ£ΌκΈ°
	@GetMapping("/list")
	public void list(Model model) {
		log.info("?? ? μ²? λͺ©λ‘ ?μ²?....");
		
		// ?λΉμ€ ?ΈμΆ?
		List<BookDTO> list = service.getList();
		
		// listλ₯? ?΄κΈ?
		model.addAttribute("list", list);
	}
	
	// ?? ?? ₯ ?Ό λ³΄μ¬μ£ΌκΈ°
	@GetMapping("/insert")
	public void insertGet() {
		log.info("?? ? λ³? ?? ₯ ?Ό λ³΄μ¬μ£ΌκΈ°....");
	}
	
	// ?? ?? ₯ ?μ²?
	@PostMapping("/insert")
	public String insertPost(BookDTO dto, RedirectAttributes rttr) {
		log.info("?? ?? ₯ ? λ³? κ°?? Έ?€κΈ?...."+dto);
		
		try {
			if(service.bookInsert(dto)) {
				log.info("?? ₯?±κ³?");
				return "redirect:/book/list";
			}
		} catch (Exception e) {
			rttr.addFlashAttribute("error", "μ½λλ₯? ??Έ??Έ?");
			return "redirect:/book/insert";
		}
		
		log.info("?? ₯?€?¨");
		return "redirect:/book/insert";
	}
	
	// ?? ?­?  ?Ό λ³΄μ¬μ£ΌκΈ°
	@GetMapping("/delete")
	public void deleteGet() {
		log.info("?? ?­?  ?Ό λ³΄μ¬μ£ΌκΈ°....");
	}
	
	// ?? ?­?  ?μ²?
	@PostMapping("/delete")
	public String deletePost(@Param("code") int code) {
		log.info("?? ?­? ...."+code);
		
		if(service.bookDelete(code)) {
			log.info("?­? ?±κ³?");
			return "redirect:/book/list";
		}
		log.info("?­? ?€?¨");
		return "redirect:/book/delete";
	}
	
	// ?? ??  ??΄μ§? λ³΄μ¬μ£ΌκΈ°
	@GetMapping("/update")
	public void updateGet() {
		log.info("?? ? λ³? ??  ??΄μ§? ?μ²?....");
	}
	
	// ?? ??  ?μ²?
	@PostMapping("/update")
	public String updatePost(@Param("code") int code, @Param("price") int price) {
		log.info("?? ?? ...."+code+" "+price);
		
		if(service.bookUpdate(code, price)) {
			log.info("?? ?±κ³?");
			return "redirect:/book/list";
		}
		log.info("?? ?€?¨");
		return "redirect:/book/update";
	}
	
	// ?? κ²?? ?Ό λ³΄μ¬μ£ΌκΈ°
		@GetMapping("/search")
		public void searchGet() {
			log.info("?? ? λ³? κ²?? ?Ό ?μ²?....");
		}
		
		// ?? κ²?? ?μ²?
		@PostMapping("/search")
		public String searchPost(String criteria, String keyword, Model model) {
			log.info("?? κ²??...."+criteria+" "+ keyword);
			
			List<BookDTO> list = service.getSerachList(criteria, keyword);
			
			model.addAttribute("list", list);
			
			return "/book/list";
		}
}
