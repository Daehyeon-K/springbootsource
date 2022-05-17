package com.study.board.service;

import com.study.board.dto.Criteria;
import com.study.board.dto.ReplyDTO;
import com.study.board.dto.ReplyPageDTO;

public interface ReplyService {
	public boolean insert(ReplyDTO insertDto);
	public ReplyDTO readRow(int rno);
	public boolean replyUpdate(ReplyDTO updateDto);
	public boolean replyDelete(int rno);
	public ReplyPageDTO getList(Criteria cri, int bno);
	public boolean getCountBno(int bno);
}
