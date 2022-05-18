package com.study.jpa.repository;

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

import com.study.jpa.entity.Memo;

// Spring Data JPA는 여러 종류의 인터페이스 기능을 통해 JPA 관련 작업을 처리
// CRUD, 페이징, 정렬 등을 인터페이스 메소드만 호출하면 처리되도록 만들어 둠

// JpaRepository 인터페이스

//MemoRepository 테스트

// 테스트 클래스 안에 작성하는 메소드는 void이고 하나만 존재해야 함
@SpringBootTest
public class MemoRepositoryTest {
	
	@Autowired
	private MemoRepository repository;
	
	
	// insert : save(엔티티 객체)
	// select : findById(키 타입), getIne(키 타입)
	// update : save(엔티티 객체)
	// delete : deleteById(키 타입), delete(엔티티)
	
	// insert
//	@Test
//	public void testInsert() {
//		IntStream.rangeClosed(1, 100).forEach(i ->{
////			Memo memo = new Memo();
////			memo.setMemoText("Sample..."+i);
//			
//			// Memo.java에서 @Builder 해둬야 위애거 대신 아래가 가능
//			// 예를 들어 new Memo(1,2,3,4,5,6,7) 처럼 인자가 많으면 각 인자가 무엇에 대한 초기화인지 알기가 어려움
//			// 그래서 setter로 길게 쓰기도 하는데
//			// 이걸 Builder 방식으로도 가능하다. 필드명 자체를 이용하며 값을 넣어주는
//			Memo memo = Memo.builder()
//							.memoText("Sample..."+i)
//							.build();
//			
//			// Entity Manager가 엔티티를 관리하며 객체를 비교한 후 없으면 insert작업 진행
//			// 											있으면 update로 save 실행해줌
//			repository.save(memo);
//		});
//	}
	
	// read
//	@Test
//	public void testRead() {
//		// Optional : 결과 등이 있을 수도, 없을 수도 있을 때
//		Optional<Memo> result = repository.findById(90L);
//		
//		// 레코드를 읽을 때 리턴값이 있으면 이 안의 isPresent가 true 됨
//		if(result.isPresent()) {
//			System.out.println(result.get());
//		}
//	}
	
	// update
//	@Test
//	public void testUpdate() {
//		// 수정 내용
//		Memo memo = Memo.builder()
//						.mno(90L)
//						.memoText("업데이트 메모")
//						.build();
//		
//		// insert와 같이 save지만 있는지 없는지 따라 이렇게 수정이 되기도 함
//		System.out.println(repository.save(memo));
//	}
	
	// delete
//	@Test
//	public void testDelete() {
//		repository.deleteById(90L);
//	}
	
	// 페이징
//	@Test
//	public void testPaging() {
//		
//		// 페이징 처리 : findAll(Pageable)
//		// Pageable : 페이지 처리에 필요한 정보를 전달하는 객체 (springfarework.data.domain 인터페이스 부르기)
//		
//		// 페이지 번호는 0번 부터 시작
//		// 1페이지 데이터 10개 가져오기 같은 개념
//		
//		// Sort 없을 때 ascending 개념일 때
//		Pageable pageable = PageRequest.of(0, 10); 
//		Page<Memo> result = repository.findAll(pageable);
//		
//		// Sort 넣어서 (springfarework.data.domain 인터페이스 부르기)
//		Sort sort = Sort.by("mno").descending();
//		Pageable pageable = PageRequest.of(0, 10, sort);
//		
//		Page<Memo> result = repository.findAll(pageable);
//		
//		System.out.println(result);
//		
//		System.out.println("------------------------------");
//		
//		System.out.println("총 페이지 수 : "+result.getTotalPages());
//		System.out.println("전체 게시물 수 : "+result.getTotalElements());
//		System.out.println("현재 페이지 번호 (0부터 시작) : "+result.getNumber());
//		System.out.println("페이지 당 데이터 개수 : "+result.getSize());
//		System.out.println("다음 페이지 존재 여부 : "+result.isFirst());
//		
//		System.out.println("------------------------------");
//		
//		for(Memo memo : result.getContent()) {
//			System.out.println(memo);
//		}
//	}
	
	// 이런 것 말고 동적 SQL 등 없는 것은 직접 만들어야 하고, 또 굳이 아니더라도 위에 것들 말고도 메소드 많음
		
	// find QueryMethod
//	@Test
//	public void testQuery() {
//		List<Memo> list =  repository.findByMnoBetweenOrderByMnoDesc(40L, 80L);
//		
//		list.forEach(memo -> {
//			System.out.println(memo);
//		});
//	}
	
	// find QueryMethod
	@Test
	public void testQuery2() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
		
		Page<Memo> result =  repository.findByMnoBetween(40L, 80L, pageable);
		
		result.getContent().forEach(memo -> {
			System.out.println(memo);
		});
	}
	
}
