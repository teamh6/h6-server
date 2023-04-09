package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.member.MemberRequestLogin;
import com.example.demo.dto.member.MemberRequestSignUp;
import com.example.demo.dto.member.MemberResponse;
import com.example.demo.service.MemberService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	// 회원 관련 컨트롤러 -> 나중에 /api를 => /member로 수정 필요할듯 ~> react에서 axios로 요청보낼때도 변경 필요

	private final MemberService memberService;

	@PostMapping("/login")
	public MemberResponse login(@RequestBody final MemberRequestLogin request) {
		System.out.println(request);
		return memberService.findBy(request);
	}

	// 이메일 중복 체크
	@GetMapping("/signup/{email}")
	public int checkEmail(@PathVariable String email) {
		System.out.println(email);
		return memberService.checkEmail(email);
	}

	// insert 결과값을 던져줌.
	@PostMapping("/signup")
	public int signUp(@RequestBody final MemberRequestSignUp request) {
		return memberService.signUp(request);
	}

}
