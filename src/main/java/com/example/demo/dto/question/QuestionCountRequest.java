package com.example.demo.dto.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class QuestionCountRequest {
	private int member_id;
	private int answer; // 0 : 답변x, 1 : 답변완료

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "member_id:" + member_id + ", answer : " + answer;
	}
}
