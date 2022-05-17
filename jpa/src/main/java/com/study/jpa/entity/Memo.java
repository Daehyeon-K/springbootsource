package com.study.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 데이터베이스 Entity : DB 상에서 데이터로 관리하는 대상, 예를 들어 상품, 회사, 직원 등과 같이 명사이면서 업무와 관련된 데이터 테이블 

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name="memotbl")
@SequenceGenerator(name="mem_seq_gen", sequenceName="mem_seq", allocationSize=1)
@Entity // 클래스가 Entity임을 명시 (Entity Manager가 관리)
public class Memo {
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mem_seq_gen")
	@Id
	private Long mno;
	
	@Column(length = 200, nullable=false)
	private String memoText;
	
}

// @Table : @Entity와 같이 사용, 엔티티 클래스를 어떤 테이블로 생성할 것인가?

// 아래 두개는 주로 같이 씀. @Entity가 붙은 클래스는 primary key에 해당하는 특정 필드를 @Id로 지정해야만 함
// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mem_seq_gen") : 어떤 식으로 PK를 생성해서 삽입할 지
// @Id : PK 지정

// GenerationType.SEQUENCE : 오라클. @SequenceGenerator(name="mem_seq_gen", sequenceName="mem_seq", allocationSize=1)와 함께 사용
// GenerationType.IDENTITY : 사용하는 DB(MySQL, MariaDB 등)가 키 생성을 결정 (오라클은 에러남)
// GenerationType.AUTO (이게 Default) : 특정 DB(MySQL, MariaDB 등)에 맞게 자동 생성됨 (오라클은 에러남)
// GenerationType.TABLE : 키 생성 전용 테이블 사용. @TableGenerator와 함께 사용

// @SequenceGenerator(name="mem_seq_gen", sequenceName="mem_seq", allocationSize=1)
// name: Sequence Generator 이름 (필수)
// sequenceName: 시퀀스 이름(옵션)
// allocationSize=1 : 옵션. 지정 안할 시 50씩 증가
// initialValue=1 : (1이 기본값)