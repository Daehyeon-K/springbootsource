package com.study.board.service;

import java.util.List;
import com.study.board.dto.Criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.board.dto.AttachDTO;
import com.study.board.dto.BoardDTO;
import com.study.board.mapper.AttachMapper;
import com.study.board.mapper.BoardMapper;
import com.study.board.mapper.ReplyMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return mapper.select(cri);
	}
	
	@Transactional
	@Override
	public void insert(BoardDTO insertDto) {
		
		// 새 글 등록
		mapper.insert(insertDto);
		
		// 첨부파일이 없다면 되돌려 보내기
		if(insertDto.getAttachList() == null || insertDto.getAttachList().size() <= 0) return;
		
		// 첨부파일 개수만큼 루프 돌기
		insertDto.getAttachList().forEach(attach -> {
			attach.setBno(insertDto.getBno());
			// 첨부파일 삽입
			attachMapper.insert(attach);
		});
	}

	@Override
	public BoardDTO getRow(int bno) {
		return mapper.read(bno);
	}
	
	@Transactional
	@Override
	public boolean update(BoardDTO updateDto) {
		
		// 기존 첨부파일 삭제
		attachMapper.deleteAll(updateDto.getBno());
		
		if(updateDto.getAttachList() != null && updateDto.getAttachList().size() > 0) {
			for(AttachDTO attach:updateDto.getAttachList()) {
				attach.setBno(updateDto.getBno());
				attachMapper.insert(attach);
			}
		}
		
		return mapper.update(updateDto)==1?true:false;
	}
	
	@Transactional
	@Override
	public boolean delete(int bno) {
		// 글을 삭제하며 첨부파일 삭제
		attachMapper.deleteAll(bno);
		
		// 글을 삭제하며 댓글 삭제
		replyMapper.deleteAll(bno);
		
		return mapper.delete(bno)==1?true:false;
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public List<AttachDTO> attachList(int bno) {
		return attachMapper.list(bno);
	}

}
