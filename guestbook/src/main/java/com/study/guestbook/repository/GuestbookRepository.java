package com.study.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.study.guestbook.entity.GuestBook;

public interface GuestbookRepository extends JpaRepository<GuestBook, Long>, QuerydslPredicateExecutor<GuestBook> {
	// CRUD 작업, 정렬, 페이징 메소드 가지고 있음
	// 스프링이 자동으로 관리함
	
	
}
