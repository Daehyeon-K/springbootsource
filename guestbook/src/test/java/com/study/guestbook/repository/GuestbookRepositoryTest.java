package com.study.guestbook.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.study.guestbook.entity.GuestBook;
import com.study.guestbook.entity.QGuestBook;

@SpringBootTest
public class GuestbookRepositoryTest {
	
	@Autowired
	private GuestbookRepository repository;
	
//	@Test
//	public void insert() {
//		
//		IntStream.rangeClosed(1, 300).forEach(i -> {
//			GuestBook guestbook = GuestBook.builder()
//										   .title("Guest Title..."+i)
//										   .content("Guest Content..."+i)
//										   .writer("user..."+(i%10))
//										   .build();
//			System.out.println(repository.save(guestbook));
//		});
//		
//	}
	
//	@Test
//	public void update() {
//		// 15001번 먼저 찾고 이후 수정
//		repository.findById(15001L).ifPresent(guest ->{
//			guest.setTitle("Changed Title...");
//			guest.setContent("Changed Content...");
//			
//			System.out.println(repository.save(guest));
//		});
//		
//	}
	
	
	// querydsl 테스트
	// 검색 시 쓸 것
	// 단일 항목 검색(제목, 내용, 작성자, ~)
	// 혼합 항목 검색(제목+내용, 내용+작성자, 제목+작성자, 제목+내용+작성자, ~)
	
	// 단일 항목 검색
//	@Test
//	public void searchQuery() {
//		
//		// 페이지 나누기
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
//		
//		// Q도메인 클래스 이용
//		QGuestBook qGuestBook = QGuestBook.guestBook;
//		
//		// title에 1이 들어가 있는 게시물 검색 (where title like '%1%')
//		String keyword ="1";
//		
//		// where절에 들어가는 조건들을 넣어주는 컨테이너
//		BooleanBuilder builder = new BooleanBuilder();
//		
//		// 뒤 조건식 표현부를 만들어주는 부분
//		BooleanExpression expression = qGuestBook.title.contains(keyword);
//		
//		// 표현식 조건을 빌더 컨테이너에 넣음
//		builder.and(expression);
//		
//		// 페이지 나누기 + 생성해 낸 where
//		// repository의 findAll 이용한 작업
//		Page<GuestBook> result = repository.findAll(builder, pageable);
//		
//		result.stream().forEach(guestbook ->{
//			System.out.println(guestbook);
//		});
//		
//	}
	
	// 혼합 항목 검색
	@Test
	public void searchQuery() {
		
		// 페이지 나누기
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
		
		// Q도메인 클래스 이용
		QGuestBook qGuestBook = QGuestBook.guestBook;
		
		// 제목 혹은 내용에 1이라는 키워드가 있고, gno가 0보다 크다는 조건
		// where gno>0 and (title like '%1%' or content like '%1%')
		String keyword ="1";
		
		// where절에 들어가는 조건들을 넣어주는 컨테이너
		BooleanBuilder builder = new BooleanBuilder();
		
		// 뒤 제목 혹은 내용 조건식 표현부를 만들어주는 부분
		BooleanExpression expTitle = qGuestBook.title.contains(keyword);
		BooleanExpression expContent = qGuestBook.content.contains(keyword);
		BooleanExpression expAll = expTitle.or(expContent);
		
		// 표현식 조건을 빌더 컨테이너에 넣음
		builder.and(expAll);
		
		// gno > 0 부분
		builder.and(qGuestBook.gno.gt(0L));
		
		// 페이지 나누기 + 생성해 낸 where
		// repository의 findAll 이용한 작업
		Page<GuestBook> result = repository.findAll(builder, pageable);
		
		result.stream().forEach(guestbook ->{
			System.out.println(guestbook);
		});
		
	}
	
}
