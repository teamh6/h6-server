package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.question.QuestionResponse;

@Mapper
@Repository
public interface QuestionRepository {
//	전체조회
	@Select("select * from question")
	List<QuestionResponse> getAllQuestion();

//	답변이 없는것 조회 - 관리자
	@Select("select * from question where answer is null")
	List<QuestionResponse> getNoAnswer();

	// 답변이 있는 것 조회 - 관리자
	@Select("select * from question where answer is not null")
	List<QuestionResponse> getHasAnswer();

	// 답변이 있는 것 조회 - 회원
	@Select("select * from question where member_id = #{member_id} and answer is not null")
	List<QuestionResponse> getMyHasAnswer(@Param("member_id") int member_id);

// 나의 문의사항 조회
	@Select("select * from question where member_id = #{member_id}")
	List<QuestionResponse> getMyQuestion(@Param("member_id") int member_id);

// 답변이 없는것 조회 - 회원
	@Select("select * from question where member_id = #{member_id} and answer is null")
	List<QuestionResponse> getMyNoAnswer(@Param("member_id") int member_id);

//  상세조회 - 관리자, 회원
	@Select("select * from question where question_id = #{question_id}")
	public QuestionResponse selectQuestion(@Param("question_id") int question_id);

//	게시글 작성 후 작성된 행 수 반환
	@SelectKey(statement = "select count(*) from question where member_id = #{member_id} and  title = #{title} and content = #{content}", before = false, keyProperty = "count", resultType = int.class)
	@Insert("insert into question(member_id, title, content) values(#{member_id}, #{title}, #{content})")
	int insertQuestion(@Param("member_id") int member_id, @Param("title") String title,
			@Param("content") String content);

//	본인글 수정 후 수정된 행의 수 반환
	@SelectKey(statement = "select count(*) from question where question_id = #{question_id}", before = false, keyProperty = "count", resultType = int.class)
	@Update("update question set title = #{title}, content = #{content} where question_id = #{question_id}")
	int updateQuestion(@Param("question_id") int question_id, @Param("title") String title,
			@Param("content") String content);

//	답변 후 수정된 행의 수 반환
	@SelectKey(statement = "select count(*) from question where answer = #{answer} and question_id = #{question_id}", before = false, keyProperty = "count", resultType = int.class)
	@Update("update question set answer = #{answer}, answer_date = now() where question_id = #{question_id}")
	int answerQuestion(@Param("answer") String answer, @Param("question_id") int question_id);

//	삭제
	@SelectKey(statement = "select count(*) from question where question_id = #{question_id}", before = true, keyProperty = "count", resultType = int.class)
	@Delete("delete from question where question_id = #{question_id}")
	int deleteQuestion(@Param("question_id") int question_id);

	// 전체 질문 개수 조회 - 관리자
	@Select("select count(*) from question ")
	int countAllQuestion();

	// 전체 질문 개수 조회 - 회원
	@Select("select count(*) from where member_id = #{member_id}")
	int countMyAllQuestion(@Param("member_id") int member_id);

	// 답변이 완료 되지 않은 질문 개수 조회 - 관리자
	@Select("select count(*) from question where answer is null")
	int countNoAnswer();
	
	// 답변이 완료된 않은 질문 개수 조회 - 관리자
	@Select("select count(*) from question where answer is not null")
	int countAnswer();

	// 답변이 완료되지 않은 질문 개수 조회 - 회원
	@Select("select count(*) from question where answer is null and member_id = 	#{member_id}")
	int countMyNoAnswer(@Param("member_id") int member_id);

	// 답변이 완료된 질문 개수 조회 - 회원
	@Select("select count(*) from question where answer is not null and member_id = #{member_id}")
	int countMyAnswer(@Param("member_id") int member_id);
}
