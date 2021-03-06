package com.study.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.book.dto.BookDTO;

@Mapper
public interface BookMapper {
	
	//CRUD ??? ??? ๋ฉ์? ? ?ธ
	
	// Create - ?ฝ?
	public int insert(BookDTO insertDto);
	
	// Read - ? ์ฒ? ์กฐํ, ๊ฐ๋ณ ์กฐํ, ๊ฒ??
	public List<BookDTO> list();
	public BookDTO select(int code);
	public List<BookDTO> search(@Param("criteria") String criteria, @Param("keyword") String keyword);
	
	// Update - ?? 
	public int update(@Param("code") int code, @Param("price") int price);
	
	// Delete - ?ญ? 
	public int delete(int code);
}
