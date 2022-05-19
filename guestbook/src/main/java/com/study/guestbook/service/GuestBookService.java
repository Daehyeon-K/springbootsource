package com.study.guestbook.service;

import com.study.guestbook.dto.GuestBookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.dto.PageResultDTO;
import com.study.guestbook.entity.GuestBook;

public interface GuestBookService {
	// 등록
	Long register(GuestBookDTO insertDto);
	
	// 목록
	PageResultDTO<GuestBookDTO,GuestBook> getList(PageRequestDTO requestDto);
	
	// 조회
	GuestBookDTO read(Long gno);
	
	// 삭제
	void remove(Long gno);
	
	// 수정
	void modify(GuestBookDTO updateDto);
	
	// Entity(GuestBook)를 DTO(GuestBookDTO)로 변환하기
	default GuestBookDTO entityToDto(GuestBook entity) {
		GuestBookDTO dto = GuestBookDTO.builder()
									   .gno(entity.getGno())
									   .title(entity.getTitle())
									   .content(entity.getContent())
									   .writer(entity.getWriter())
									   .regDate(entity.getRegDate())
									   .updateDate(entity.getUpdateDate())
									   .build();
		return dto;
	}
	
	// DTO(GuestBookDTO)를 Entity(GuestBook)로 변환하기
	default GuestBook dtoToEntity(GuestBookDTO dto) {
		GuestBook entity = GuestBook.builder()
									.title(dto.getTitle())
									.content(dto.getContent())
									.writer(dto.getWriter())
									.build();
		return entity;
	}
}
