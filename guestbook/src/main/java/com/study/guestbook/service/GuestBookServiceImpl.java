package com.study.guestbook.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.study.guestbook.dto.GuestBookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.dto.PageResultDTO;
import com.study.guestbook.entity.GuestBook;
import com.study.guestbook.entity.QGuestBook;
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
		
		// 검색
		BooleanBuilder booleanBuilder = getSearch(requestDto);
		
		// findAll 호출
		Page<GuestBook> result = repository.findAll(booleanBuilder,pageable);
		
		// 함수형 인터페이스
		Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDto(entity));
		
		return new PageResultDTO<GuestBookDTO, GuestBook>(result,fn);
	}

	@Override
	public GuestBookDTO read(Long gno) {
		Optional<GuestBook> result = repository.findById(gno);
		
		// 화면에서 사용할 때는 DTO
		// DB, 서버에서 사용할 때는 Entity
		
		return result.isPresent() ? entityToDto(result.get()) : null;
	}

	@Override
	public void remove(Long gno) {
		repository.deleteById(gno);
	}

	@Override
	public void modify(GuestBookDTO updateDto) {
		Optional<GuestBook> result = repository.findById(updateDto.getGno());
		
		if(result.isPresent()) {
			GuestBook entity = result.get();
			
			entity.setTitle(updateDto.getTitle());
			entity.setContent(updateDto.getContent());
			
			repository.save(entity);
		}
	}
	
	private BooleanBuilder getSearch(PageRequestDTO requestDto) {
		// 검색 조건 가져오기
		String type = requestDto.getType();
		
		// 검색어 가져오기
		String keyword = requestDto.getKeyword();
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QGuestBook qGuestBook = QGuestBook.guestBook;
		
		BooleanExpression expression = qGuestBook.gno.gt(0L);
		booleanBuilder.and(expression);
		
		if(type == null || type.trim().length() == 0) {
			return booleanBuilder;
		}
		
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		
		if(type.contains("t")) {
			conditionBuilder.or(qGuestBook.title.contains(keyword));
		}
		if(type.contains("c")) {
			conditionBuilder.or(qGuestBook.content.contains(keyword));
		}
		if(type.contains("w")) {
			conditionBuilder.or(qGuestBook.writer.contains(keyword));
		}
		
		booleanBuilder.and(conditionBuilder);
		
		return booleanBuilder;
	}

}
