package com.study.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String list(PageRequestDTO requestDto, Model model) {
		log.info("list 요청" + requestDto);
		
		// 목록 리스트 생성 - PageResultDTO<GuestBookDTO, GuestBook> 타입으로 넘어감
		model.addAttribute("result", service.getList(requestDto));
		
		return "/guestbook/list";
	}
	
}
