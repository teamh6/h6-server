package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.model.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MemberResponseDto { // 단일 책임원칙 -> DTO가 하는일이 많아지기 때문에 object mapper에게 위임.
	//objectmapper를 빈으로 등록하여 이걸 작업 해준다.
	private int member_id;
	private String email;
	private String password;
	private String name;
	private String phone_number;
	private LocalDateTime register_date;
	private int admin;
	
	//@AllArgsConstructor를 쓰면 없어도 됨.	
	public MemberResponseDto(Member member) {
		this.member_id = member.getMember_id();
		this.email = member.getEmail();
		this.password = member.getPassword();
		this.name = member.getName();
		this.phone_number = member.getPhone_number();
		this.register_date = member.getRegister_date();
		this.admin = member.getAdmin();
	}
	
	//디버거를 코드 흐름 볼때 많이 씀 -> 익혀놓기!

	//위와 같은 constructor 방식보다는 build 메서드에서 빌더 방식으로 객체를 만들어서 보낸는게 좋다. -> 더 좋은 건 object mapper
//	public static build(Member member) {
//		Member
//	}

	//object mapper? -> map structure라는 라이브러리가 있다.
	//Member라는 obejct를 MemberResponseDto로 변환 시킬 것이기 때문에
	//한 오브젝트를 다른 오브젝트로 변환 시켜주는 라이브러리
}
