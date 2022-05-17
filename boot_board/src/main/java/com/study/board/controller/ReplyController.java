package com.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ServletResponseMethodArgumentResolver;

import com.study.board.dto.Criteria;
import com.study.board.dto.ReplyDTO;
import com.study.board.dto.ReplyPageDTO;
import com.study.board.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/replies/*")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	
	// 댓글 삽입 - /replies/new + POST + body (댓글내용 - json)
	// 성공 시 success + 200, 실패 시 fail + 500
	// create
	// consumes : 받아서 처리할 컨텐츠 타입
	@PreAuthorize("isAuthenticated()")
	@PostMapping(path="/new", consumes = "application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody ReplyDTO insertDto){
		log.info("댓글 삽입 요청 "+insertDto);
		return service.insert(insertDto)?new ResponseEntity<String>("success", HttpStatus.OK):new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 하나 가져오기 - /replies/rno + GET (댓글 번호)
	// 성공 시 ReplyDTO + 200
	// read
	@GetMapping(path="/{rno}")
	public ResponseEntity<ReplyDTO> get(@PathVariable("rno") int rno){
		log.info("댓글 가져오기 "+rno);
		
		return new ResponseEntity<ReplyDTO>(service.readRow(rno), HttpStatus.OK);
	}
	
	// 댓글 수정 - /replies/rno + PUT + body(수정내용 - json)
	// 성공 시 success + 200, 실패 시 fail + 500
	// update
	
	// @RequestMapping(path="/{rno}", method=RequestMethod.PUT) => PUT 매핑을 requestmapping으로 주는 경우
	// @RequestMapping(path="/{rno}", method=RequestMethod.PATCH) => PATCH 매핑을 requestmapping으로 주는 경우
	// @RequestMapping(path="/{rno}", method=(RequestMethod.PUT, RequestMethod.PATCH)) => PUT 매핑, PATCH 매핑을 requestmapping으로 주는 경우
	
	@PreAuthorize("principal.username == #updateDto.replyer")
	@PutMapping("/{rno}")
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyDTO updateDto){
		log.info("댓글 수정 요청 "+updateDto);
		updateDto.setRno(rno);
		return service.replyUpdate(updateDto)?new ResponseEntity<String>("success", HttpStatus.OK):new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글삭제 : /replies/rno + delete
	// delete
	
	@PreAuthorize("principal.username == #dto.replyer")
	@DeleteMapping(path="/{rno}")
	public ResponseEntity<String> delete(@PathVariable("rno") int rno, @RequestBody ReplyDTO dto){
		log.info("댓글 삭제 요청 "+rno);
		return service.replyDelete(rno)?new ResponseEntity<String>("success", HttpStatus.OK):new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 리스트 가져오기 : /replies/pages/bno/page + GET
	// 성공 시 댓글 리스트
	// http://localhost:9090/replies/pages/456/1
	@GetMapping("/pages/{bno}/{page}")
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("bno") int bno, @PathVariable("page") int page){
		log.info("댓글 리스트 요청 "+bno+" "+page);
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<ReplyPageDTO>(service.getList(cri, bno), HttpStatus.OK);
	}
	
	
}
