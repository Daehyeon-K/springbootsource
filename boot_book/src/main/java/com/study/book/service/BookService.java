package com.study.book.service;

import java.util.List;

import com.study.book.dto.BookDTO;

public interface BookService {
	// BookMapper ë©”ì†Œ?“œ ?˜¸ì¶?
	public boolean bookInsert (BookDTO insertDto);
	public List<BookDTO> getList();
	public BookDTO getRow(int code);
	public List<BookDTO> getSerachList(String criteria, String keyword);
	public boolean bookUpdate(int code, int price);
	public boolean bookDelete(int code);
}
