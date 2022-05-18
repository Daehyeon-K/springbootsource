package com.study.guestbook.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.study.guestbook.dto.GuestBookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.dto.PageResultDTO;
import com.study.guestbook.entity.GuestBook;
import com.study.guestbook.repository.GuestbookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GuestBookServiceImpl implements GuestBookService {

	@Autowired
	private GuestbookRepository repository;
	
	@Override
	public Long register(GuestBookDTO insertDto) {
		log.info("service register "+insertDto);
		
		// entity 타입으로 변환
		GuestBook entity = dtoToEntity(insertDto);
		
		log.info("entity "+entity);
		
		// DB 작업
		repository.save(entity);
		
		return entity.getGno();
	}

	@Override
	public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDto) {
		
		// Sort 기준
		Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());
		
		// findAll 호출
		Page<GuestBook> result = repository.findAll(pageable);
		
		// 함수형 인터페이스
		Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDto(entity));
		
		return new PageResultDTO<GuestBookDTO, GuestBook>(result,fn);
	}

}
