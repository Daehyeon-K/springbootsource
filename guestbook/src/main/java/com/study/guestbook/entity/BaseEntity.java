package com.study.guestbook.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;

/* 엔티티 작업 시 데이터의 등록 시긴, 수정시간과 같이 자동으로 추가되고 변경되어야 하는 컬럼 존재함
 * 시간 입력 - 등록 시간, 수정 시간 => 프로그램 내에서 처리하는 것 보다 자동으로 처리가 되도록 설정
 * 
 * @MappedSuperclass : 테이블로 생성하지 말 것
 * @CreatedDate : 엔티티의 생성 시간
 * @LastModifiedDate : 엔티티의 최종 수정 시간
 * ==> updatable=false 지정 시 엔티티의 변화가 일어나도 컬럼을 업데이트 하지 않음. (default가 true) 
 * 
 * @EntityListeners(AuditingEntityListener.class) : 엔티티 매니저가 관리하는 Persistence Context 변화를 감지하는 리스너 => 그래야 date 등의 자동 변경이 가능
 */


import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity {
	
	@CreatedDate
	@Column(name = "regdate",updatable=false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name = "updatedate")
	private LocalDateTime updateDate;
	
}
