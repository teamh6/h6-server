package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.dto.member.MemberRequestLogin;
import com.example.demo.dto.member.MemberRequestSignUp;
import com.example.demo.dto.member.MemberResponse;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberResponse findBy(final MemberRequestLogin request) {

		try {
			MemberResponse response = memberRepository.selectMember(request.getEmail(), request.getPassword());
			System.out.println(response.getEmail() + "님이 로그인 함.");
			return response;

		} catch (Exception e) {
			System.out.println("아이디 혹은 비밀번호가 틀림");
			MemberResponse response = new MemberResponse(-1, null, null, null, null, 0);
			return response;
		}

	}

	// 중복 아이디 체크
	public int checkEmail(String email) {
		int result = memberRepository.selectMemberEmail(email);
		if (result == 1) {// 중복된 놈이 있을 때
			return -1;
		}
		return 1;// 중복된 놈이 없을 때
	}

	// 회원 가입
	public int signUp(final MemberRequestSignUp request) {
		int result = 0;
		try {
			// 들어간 행
			result = memberRepository.signUp(request.getEmail(), request.getPassword(), request.getName(),
					request.getPhone_number());

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return -9999; // 오류시 에러 코드 -9999설정
		}
		System.out.println("signUp : " + result + "행 들어감");
		return result;
	}

}
