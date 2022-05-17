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
	
	// ? „ì²? ë¦¬ìŠ¤?Š¸ ë³´ì—¬ì£¼ê¸°
	@GetMapping("/list")
	public void list(Model model) {
		log.info("?„?„œ ? „ì²? ëª©ë¡ ?š”ì²?....");
		
		// ?„œë¹„ìŠ¤ ?˜¸ì¶?
		List<BookDTO> list = service.getList();
		
		// listë¥? ?‹´ê¸?
		model.addAttribute("list", list);
	}
	
	// ?„?„œ ?…? ¥ ?¼ ë³´ì—¬ì£¼ê¸°
	@GetMapping("/insert")
	public void insertGet() {
		log.info("?„?„œ ? •ë³? ?…? ¥ ?¼ ë³´ì—¬ì£¼ê¸°....");
	}
	
	// ?„?„œ ?…? ¥ ?š”ì²?
	@PostMapping("/insert")
	public String insertPost(BookDTO dto, RedirectAttributes rttr) {
		log.info("?„?„œ ?…? ¥ ? •ë³? ê°?? ¸?˜¤ê¸?...."+dto);
		
		try {
			if(service.bookInsert(dto)) {
				log.info("?…? ¥?„±ê³?");
				return "redirect:/book/list";
			}
		} catch (Exception e) {
			rttr.addFlashAttribute("error", "ì½”ë“œë¥? ?™•?¸?•˜?„¸?š”");
			return "redirect:/book/insert";
		}
		
		log.info("?…? ¥?‹¤?Œ¨");
		return "redirect:/book/insert";
	}
	
	// ?„?„œ ?‚­? œ ?¼ ë³´ì—¬ì£¼ê¸°
	@GetMapping("/delete")
	public void deleteGet() {
		log.info("?„?„œ ?‚­? œ ?¼ ë³´ì—¬ì£¼ê¸°....");
	}
	
	// ?„?„œ ?‚­? œ ?š”ì²?
	@PostMapping("/delete")
	public String deletePost(@Param("code") int code) {
		log.info("?„?„œ ?‚­? œ...."+code);
		
		if(service.bookDelete(code)) {
			log.info("?‚­? œ?„±ê³?");
			return "redirect:/book/list";
		}
		log.info("?‚­? œ?‹¤?Œ¨");
		return "redirect:/book/delete";
	}
	
	// ?„?„œ ?ˆ˜? • ?˜?´ì§? ë³´ì—¬ì£¼ê¸°
	@GetMapping("/update")
	public void updateGet() {
		log.info("?„?„œ ? •ë³? ?ˆ˜? • ?˜?´ì§? ?š”ì²?....");
	}
	
	// ?„?„œ ?ˆ˜? • ?š”ì²?
	@PostMapping("/update")
	public String updatePost(@Param("code") int code, @Param("price") int price) {
		log.info("?„?„œ ?ˆ˜? •...."+code+" "+price);
		
		if(service.bookUpdate(code, price)) {
			log.info("?ˆ˜? •?„±ê³?");
			return "redirect:/book/list";
		}
		log.info("?ˆ˜? •?‹¤?Œ¨");
		return "redirect:/book/update";
	}
	
	// ?„?„œ ê²??ƒ‰ ?¼ ë³´ì—¬ì£¼ê¸°
		@GetMapping("/search")
		public void searchGet() {
			log.info("?„?„œ ? •ë³? ê²??ƒ‰ ?¼ ?š”ì²?....");
		}
		
		// ?„?„œ ê²??ƒ‰ ?š”ì²?
		@PostMapping("/search")
		public String searchPost(String criteria, String keyword, Model model) {
			log.info("?„?„œ ê²??ƒ‰...."+criteria+" "+ keyword);
			
			List<BookDTO> list = service.getSerachList(criteria, keyword);
			
			model.addAttribute("list", list);
			
			return "/book/list";
		}
}
