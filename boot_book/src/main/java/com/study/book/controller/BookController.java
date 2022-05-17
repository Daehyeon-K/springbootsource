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
	
	// ?���? 리스?�� 보여주기
	@GetMapping("/list")
	public void list(Model model) {
		log.info("?��?�� ?���? 목록 ?���?....");
		
		// ?��비스 ?���?
		List<BookDTO> list = service.getList();
		
		// list�? ?���?
		model.addAttribute("list", list);
	}
	
	// ?��?�� ?��?�� ?�� 보여주기
	@GetMapping("/insert")
	public void insertGet() {
		log.info("?��?�� ?���? ?��?�� ?�� 보여주기....");
	}
	
	// ?��?�� ?��?�� ?���?
	@PostMapping("/insert")
	public String insertPost(BookDTO dto, RedirectAttributes rttr) {
		log.info("?��?�� ?��?�� ?���? �??��?���?...."+dto);
		
		try {
			if(service.bookInsert(dto)) {
				log.info("?��?��?���?");
				return "redirect:/book/list";
			}
		} catch (Exception e) {
			rttr.addFlashAttribute("error", "코드�? ?��?��?��?��?��");
			return "redirect:/book/insert";
		}
		
		log.info("?��?��?��?��");
		return "redirect:/book/insert";
	}
	
	// ?��?�� ?��?�� ?�� 보여주기
	@GetMapping("/delete")
	public void deleteGet() {
		log.info("?��?�� ?��?�� ?�� 보여주기....");
	}
	
	// ?��?�� ?��?�� ?���?
	@PostMapping("/delete")
	public String deletePost(@Param("code") int code) {
		log.info("?��?�� ?��?��...."+code);
		
		if(service.bookDelete(code)) {
			log.info("?��?��?���?");
			return "redirect:/book/list";
		}
		log.info("?��?��?��?��");
		return "redirect:/book/delete";
	}
	
	// ?��?�� ?��?�� ?��?���? 보여주기
	@GetMapping("/update")
	public void updateGet() {
		log.info("?��?�� ?���? ?��?�� ?��?���? ?���?....");
	}
	
	// ?��?�� ?��?�� ?���?
	@PostMapping("/update")
	public String updatePost(@Param("code") int code, @Param("price") int price) {
		log.info("?��?�� ?��?��...."+code+" "+price);
		
		if(service.bookUpdate(code, price)) {
			log.info("?��?��?���?");
			return "redirect:/book/list";
		}
		log.info("?��?��?��?��");
		return "redirect:/book/update";
	}
	
	// ?��?�� �??�� ?�� 보여주기
		@GetMapping("/search")
		public void searchGet() {
			log.info("?��?�� ?���? �??�� ?�� ?���?....");
		}
		
		// ?��?�� �??�� ?���?
		@PostMapping("/search")
		public String searchPost(String criteria, String keyword, Model model) {
			log.info("?��?�� �??��...."+criteria+" "+ keyword);
			
			List<BookDTO> list = service.getSerachList(criteria, keyword);
			
			model.addAttribute("list", list);
			
			return "/book/list";
		}
}
