package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberRequestDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberResponseDto findBy(final MemberRequestDto params) {
		MemberResponseDto dto = memberRepository.selectMember(params.getEmail(), params.getPassword());
//		if (dto == null) {
//			throw new UserNotFoundException('laksjdflkiasjdfl;kasdjf;asdlfkj')  // 404
//		}
		// session login 처리
		return dto;
	}

}
