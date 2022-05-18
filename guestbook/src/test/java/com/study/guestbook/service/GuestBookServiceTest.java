package com.study.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.guestbook.dto.GuestBookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.dto.PageResultDTO;
import com.study.guestbook.entity.GuestBook;

@SpringBootTest
public class GuestBookServiceTest {
	
	@Autowired
	private GuestBookService service;
	
//	@Test
//	public void testRegister() {
//		
//		GuestBookDTO dto = GuestBookDTO.builder()
//									   .title("안녕하세요")
//									   .content("Springboot에 대해서 궁금합니다.")
//									   .writer("홍길동")
//									   .build();
//		System.out.println(service.register(dto));
//	}
	
//	@Test
//	public void testList() {
//		
//		PageRequestDTO requestDto = PageRequestDTO.builder()
//												  .page(1)
//												  .size(10)
//												  .build();
//		
//		 PageResultDTO<GuestBookDTO, GuestBook> result = service.getList(requestDto);
//		 
//		 result.getDtoList().forEach(guestbook -> {
//			 System.out.println(guestbook);
//		 });
//	}
	
	@Test
	public void testList() {
		
		PageRequestDTO requestDto = PageRequestDTO.builder()
												  .page(1)
												  .size(10)
												  .build();
		
		 PageResultDTO<GuestBookDTO, GuestBook> result = service.getList(requestDto);
		 
		 System.out.println("---------------------------------");
		 System.out.println("prev : "+result.isPrev());
		 System.out.println("next : "+result.isNext());
		 System.out.println("total : "+result.getTotalPage());
		 System.out.println("---------------------------------");

		 result.getDtoList().forEach(guestbook -> {
			 System.out.println(guestbook);
		 });
		 
		 System.out.println("---------------------------------");
		 
		 result.getPageList().forEach(i -> System.out.println(i));
		 
	}
	
}
