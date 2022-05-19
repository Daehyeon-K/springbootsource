package com.study.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.guestbook.dto.GuestBookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.service.GuestBookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookService service;
	
	@GetMapping({"/","/list"})
	public String list(@ModelAttribute("requestDto") PageRequestDTO requestDto, Model model) {
		log.info("list 요청" + requestDto);
		
		// 목록 리스트 생성 - PageResultDTO<GuestBookDTO, GuestBook> 타입으로 넘어감
		model.addAttribute("result", service.getList(requestDto));
		
		return "/guestbook/list";
	}
	
	@GetMapping("/register")
	public void registerGet() {
		log.info("register get");
	}
	
	@PostMapping("/register")
	public String registerPost(GuestBookDTO insertDto, RedirectAttributes rttr) {
		log.info("register post "+insertDto);
		
		Long gno = service.register(insertDto);
		
		rttr.addFlashAttribute("msg", gno);
		
		return "redirect:/guestbook/list";
	}
	
	@GetMapping({"/read", "modify"})
	public void read(long gno, @ModelAttribute("requestDto") PageRequestDTO requestDto, Model model) {
		log.info("read 요청 "+gno);
		
		GuestBookDTO dto = service.read(gno);
		
		model.addAttribute("dto", dto);
	}
	
	@PostMapping("/remove")
	public String remove(long gno, RedirectAttributes rttr) {
		log.info("remove "+gno);
		
		service.remove(gno);
		
		rttr.addFlashAttribute("msg",gno);
		return "redirect:/guestbook/list";
	}
	
	@PostMapping("/modify")
	public String modify(GuestBookDTO updateDto, @ModelAttribute("requestDto") PageRequestDTO requestDto, RedirectAttributes rttr) {
		log.info("수정 요청 "+updateDto+" page "+requestDto);
		
		service.modify(updateDto);
		
		rttr.addAttribute("gno",updateDto.getGno());
		rttr.addAttribute("page",requestDto.getPage());
		rttr.addAttribute("type",requestDto.getType());
		rttr.addAttribute("keyword",requestDto.getKeyword());
		
		return "redirect:/guestbook/read";
	}
}
