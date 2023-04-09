package com.example.demo.dto.question;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class QuestionResponse {
	private int question_id;
	private int member_id;
	private String title;
	private String content;
	private LocalDateTime question_date;
	private String answer;
	private LocalDateTime answer_date;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "question_id : " + question_id + ", member_id : " + member_id + ",title : " + title + ", content : "
				+ content + ", answer : " + answer;
	}

}
