package com.study.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.board.dto.Criteria;
import com.study.board.dto.ReplyDTO;
import com.study.board.dto.ReplyPageDTO;
import com.study.board.mapper.BoardMapper;
import com.study.board.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public boolean insert(ReplyDTO insertDto) {
		
		// 원본 글의 댓글 수 증가
		boardMapper.updateReplyCnt(insertDto.getBno(), 1);
		
		return mapper.insert(insertDto)==1?true:false;
	}

	@Override
	public ReplyDTO readRow(int rno) {
		return mapper.read(rno);
	}

	@Override
	public boolean replyUpdate(ReplyDTO updateDto) {
		return mapper.update(updateDto)==1?true:false;
	}

	@Transactional
	@Override
	public boolean replyDelete(int rno) {
		
		// bno 알아내기
		ReplyDTO dto = mapper.read(rno);
		
		// 원본 글의 댓글 수 감소
		boardMapper.updateReplyCnt(dto.getBno(), -1);
		
		return mapper.delete(rno)==1?true:false;
	}

	@Override
	public boolean getCountBno(int bno) {
		return mapper.getCountBno(bno)==1?true:false;
	}

	@Override
	public ReplyPageDTO getList(Criteria cri, int bno) {
		return new ReplyPageDTO(mapper.getCountBno(bno), mapper.select(cri, bno));
	}

}
