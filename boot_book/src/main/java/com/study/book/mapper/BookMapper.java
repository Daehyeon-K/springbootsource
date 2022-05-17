package com.study.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.book.dto.BookDTO;

@Mapper
public interface BookMapper {
	
	//CRUD ?‘?—…?— ???•œ ë©”ì†Œ?“œ ?„ ?–¸
	
	// Create - ?‚½?…
	public int insert(BookDTO insertDto);
	
	// Read - ? „ì²? ì¡°íšŒ, ê°œë³„ ì¡°íšŒ, ê²??ƒ‰
	public List<BookDTO> list();
	public BookDTO select(int code);
	public List<BookDTO> search(@Param("criteria") String criteria, @Param("keyword") String keyword);
	
	// Update - ?ˆ˜? •
	public int update(@Param("code") int code, @Param("price") int price);
	
	// Delete - ?‚­? œ
	public int delete(int code);
}
