package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.question.QuestionCountRequest;
import com.example.demo.dto.question.QuestionRequest;
import com.example.demo.dto.question.QuestionResponse;
import com.example.demo.service.QuestionService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
	private final QuestionService questionService;

	// 전체 질문 조회 - 관리자 -> ㅇ
	@GetMapping("")
	public List<QuestionResponse> getAllQuestion() {
		return questionService.getQuestionList();
	}

	// 답변이 완료되지 않은 질문 조회 - 관리자 -> o
	@GetMapping("/none")
	public List<QuestionResponse> getNoneList() {
		return questionService.getNoneList();
	}

	// 답변이 완료된 질문 조회 - 관리자 -> o
	@GetMapping("/answer")
	public List<QuestionResponse> getAnswerList() {
		return questionService.getAnswerList();
	}

	// 내 질문 목록 조회 -> o
	@GetMapping("/my/{member_id}")
	public List<QuestionResponse> getAllQuestion(@PathVariable int member_id) {
		return questionService.getQuestionList(member_id);
	}

	// 답변이 완료된 질문 조회 - 회원 -> o
	@GetMapping("/my/answer/{member_id}")
	public List<QuestionResponse> getAnswerList(@PathVariable int member_id) {
		return questionService.getAnswerList(member_id);
	}

	// 답변이 완료되지 않은 질문 조회 - 회원 -> o
	@GetMapping("/none/{member_id}")
	public List<QuestionResponse> getNoneList(@PathVariable int member_id) {

		return questionService.getMyNoneList(member_id);
	}

	// 각 질문 조회 - 관리자, 회원 -> data처리는 client단에서 -> o
	@GetMapping("/{question_id}")
	public QuestionResponse getQuestion(@PathVariable int question_id) {
		return questionService.getQuestion(question_id);
	}

	// 질문 등록 -> o
	@PostMapping("/my/{member_id}")
	public int insertQuestion(@RequestBody QuestionRequest request) {
		System.out.println("request : " + request);
		return questionService.insertQuestion(request);
	}

	// 질문 수정 - > client단에서 회원, 관리자 분리 가능? -> o
//	@PutMapping("/{question_id}")
//	public int updateQuestion(@PathVariable int question_id, @RequestBody QuestionRequest request) {
//
//		return questionService.updateQuestion(question_id, request);
//
//	}

	// 질문 삭제 -> redirect 대신 클라이언트 단에서 삭제 되면 navigtation을 이용해 뒷 페이지로 이동해줌.
	@DeleteMapping("/{question_id}")
	public int delete(@PathVariable int question_id) {

		return questionService.delete(question_id);
	}

//	// 수정 페이지 이동(내용 가지고옴 )-> client단에서 조회해서 수정 누르면 수정페이지가 되도록 처리
//	@GetMapping("/{question_id}/fix")
//	public QuestionResponse fixQuestionGet(@PathVariable int question_id) {
//		return questionService.fixQuestionGet(question_id);
//	}

	// 답변 - 관리자
	@PutMapping("/answer/{question_id}")
	public int answer(@RequestBody QuestionResponse request, @PathVariable int question_id) {
		System.out.println(request);
		return questionService.answer(request, question_id);

	}

	// 답변이 완료 or 되지 않은 질문 수 조회 - 회원
	@PostMapping("/my/count/{member_id}")
	public int countMyToggle(@RequestBody QuestionCountRequest request) {
		System.out.println(request);
		return questionService.selectCountToggle(request);
	}

	// 답변이 완료 or 되지 않은 질문 수 조회 - 관리자
	@GetMapping("/count/{answer}")
	public int countToggle(@PathVariable("answer") int request) {
//		System.out.println("request:"+request);
		return questionService.selectCountToggle(request);
	}
}
