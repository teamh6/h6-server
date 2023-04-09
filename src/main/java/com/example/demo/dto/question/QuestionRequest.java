package com.example.demo.dto.question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class QuestionRequest {
//	private int admin; //회원, 관리자 분리하려고(client단에서) -> 회원 : 0, 관리자 : 1
	private int member_id;
	private String title;
	private String content;
//	private String answer; // null 이면 null 값이 들어갈까?
}
