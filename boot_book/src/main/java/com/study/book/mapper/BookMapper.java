package com.study.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.book.dto.BookDTO;

@Mapper
public interface BookMapper {
	
	//CRUD ?��?��?�� ???�� 메소?�� ?��?��
	
	// Create - ?��?��
	public int insert(BookDTO insertDto);
	
	// Read - ?���? 조회, 개별 조회, �??��
	public List<BookDTO> list();
	public BookDTO select(int code);
	public List<BookDTO> search(@Param("criteria") String criteria, @Param("keyword") String keyword);
	
	// Update - ?��?��
	public int update(@Param("code") int code, @Param("price") int price);
	
	// Delete - ?��?��
	public int delete(int code);
}
