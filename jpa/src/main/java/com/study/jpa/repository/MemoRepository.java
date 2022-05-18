package com.study.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.study.jpa.entity.Memo;

//Spring Data JPA는 여러 종류의 인터페이스 기능을 통해 JPA 관련 작업을 처리
//CRUD, 페이징, 정렬 등을 인터페이스 메소드만 호출하면 처리되도록 만들어 둠

//JpaRepository<Entity, Id 타입> 인터페이스

// 만들기만 하면 자동으로 스프링 빈으로 등록 됨
public interface MemoRepository extends JpaRepository<Memo, Long> {
	// 사용할 수 있는 여러 메소드 제공 됨
	
	// 메소드를 새로 만들어서 넣을 수도 있음 : 쿼리 메소드 (Spring Data JPA의 doc에 나오듯 특정 규칙 지키면서 만들어야 함)
	// mno를 기준으로 between 구문 사용하고 order by 적용 => 여러 개의 행이 조희
	// select * from memotbl where mno between 10 and 20 order by mno desc
	
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
	
	Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageble);
	
//	// 메소드 생성 + Query : 이렇게 규칙과 다르게 쓸거면 원하는 대로 만들고 쿼리문 써줘도 됨
//	@Query("select mno from memo order by mno desc")
//	List<Memo> getListDesc();
//	
//	@Query("update memo set memoText = :memoText where mno = :mno") // ?는 사용 안함 대신 :parameter명 으로 씀
//	int updateMemoText(@Param("mno") Long mno,@Param("memoText") String memoText);
//	
//	// Native SQL 처리 : 원래 SQL 구문 그대로 사용하기
//	@Query(value="select mno from memo order by mno desc", nativeQuery=true)
//	List<Memo> getNativeListDesc();
	
	
}
