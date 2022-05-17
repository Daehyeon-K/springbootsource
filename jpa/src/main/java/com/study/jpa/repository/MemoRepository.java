package com.study.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.jpa.entity.Memo;

//Spring Data JPA는 여러 종류의 인터페이스 기능을 통해 JPA 관련 작업을 처리
//CRUD, 페이징, 정렬 등을 인터페이스 메소드만 호출하면 처리되도록 만들어 둠

//JpaRepository<Entity, Id 타입> 인터페이스

// 만들기만 하면 자동으로 스프링 빈으로 등록 됨
public interface MemoRepository extends JpaRepository<Memo, Long> {
	// 사용할 수 있는 여러 메소드 제공 됨
	
	// 메소드를 새로 만들어서 넣을 수도 있음
	
}
