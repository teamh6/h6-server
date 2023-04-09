package com.example.demo.dto.member;

import lombok.Data;

@Data
public class MemberRequestSignUp {
	private String email;
	private String password;
	private String name;
	private String phone_number;
}
