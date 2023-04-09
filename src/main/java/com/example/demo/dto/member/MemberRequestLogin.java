package com.example.demo.dto.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class MemberRequestLogin {//db에 요청을 보낼 매개 변수를 담은 dto
	private String email;
	private String password;
//	public MemberRequestLogin() {
//		toString();
//	}
}
