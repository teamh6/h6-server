package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {
	int member_id;
	String email;
	String password;
	String name;
	String phone_number;
	LocalDateTime register_date;
	int admin;
	
}
