package com.example.demo.dto.member;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class MemberResponse {

	private int member_id;
	private String email;
	private String name;
	private String phone_number;
	private LocalDateTime register_date;
	private int admin; // 필수

}
