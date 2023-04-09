package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.question.QuestionCountRequest;
import com.example.demo.dto.question.QuestionRequest;
import com.example.demo.dto.question.QuestionResponse;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.WatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	private final QuestionRepository questionRepository;

	// qna 조회 - 관리자
	public List<QuestionResponse> getQuestionList() {
		List<QuestionResponse> questionList = new ArrayList<>();

		questionList = questionRepository.getAllQuestion();

		return questionList;
	}

	// 답변이 완료되지 않은 qna 조회 - 관리자
	public List<QuestionResponse> getNoneList() {
		List<QuestionResponse> noneList = new ArrayList<>();

		noneList = questionRepository.getNoAnswer();
		return noneList;
	}

	// qna 조회 - 회원
	public List<QuestionResponse> getQuestionList(int member_id) {
		List<QuestionResponse> questionList = new ArrayList<>();

		questionList = questionRepository.getMyQuestion(member_id);
		return questionList;
	}

	public List<QuestionResponse> getMyNoneList(int member_id) {
		List<QuestionResponse> noneList = new ArrayList<>();

		noneList = questionRepository.getMyNoAnswer(member_id);
		return noneList;
	}

	// 각 qna 조회 - 관리자, 회원
	public QuestionResponse getQuestion(int question_id) {

		return questionRepository.selectQuestion(question_id);
	}

	// qna 등록
	public int insertQuestion(final QuestionRequest request) {
		int result = 0;
		try {
			result = questionRepository.insertQuestion(request.getMember_id(), request.getTitle(),
					request.getContent());
			return result;
		} catch (Exception e) {
			result = -9999;
		}
		return result;

	}

	// 수정 페이지 가져옴
	public QuestionResponse fixQuestionGet(int question_id) {
		QuestionResponse questionDto = questionRepository.selectQuestion(question_id);
		return questionDto;
	}

	// 수정
//	public int updateQuestion(int question_id, final QuestionRequest request) {
//
//		int result = 0;
//		try {
//			// 회원일 경우
//			if (request.getAdmin() == 0) {
//				result = questionRepository.updateQuestion(question_id, request.getTitle(), request.getContent());
//				System.out.println(result + "행 추가됨.");
//				return result;
//			}
//			// 관리자일 경우
//			if (request.getAdmin() == 1) {
//				result = questionRepository.answerQuestion(request.getAnswer(), question_id);
//				return result;
//			}
//
//		} catch (Exception e) {
//			System.err.println("updateQuestion에러 : " + e.getMessage());
//
//			result = -9999;
//		}
//		return result;
//
//	}

	// 답변
	public int answer(QuestionResponse request, int question_id) {
		int result = questionRepository.answerQuestion(request.getAnswer(), question_id);
		return result;
	}

	// 삭제
	public int delete(int question_id) {
		int result = 0;
		try {
			result = questionRepository.deleteQuestion(question_id);
		} catch (Exception e) {
			System.err.println("deleteQuestion에러 : " + e.getMessage());
			result = -9999;
		}
		System.out.println(result + "행 삭제됨.");
		return result;
	}

	// 답변이 완료 된것
	public List<QuestionResponse> getAnswerList(int member_id) {
		return questionRepository.getMyHasAnswer(member_id);
	}

	public List<QuestionResponse> getAnswerList() {
		return questionRepository.getHasAnswer();
	}

	// 답변이 완료 or 되지 않은 수 - 회원
	public int selectCountToggle(QuestionCountRequest request) {
		int count = 0;
		if (request.getAnswer() == 0) {
			count = questionRepository.countMyNoAnswer(request.getMember_id());
			return count;
		}
		
		count = questionRepository.countMyAnswer(request.getMember_id());
		return count;
	}

	// 답변이 완료 or 되지 않은 수 - 관리자
	public int selectCountToggle(int request) {
		int count = 0;
		if (request == 0) {
			count = questionRepository.countNoAnswer();
			return count;
		}
		count = questionRepository.countAnswer();
		return count;

	}

}
