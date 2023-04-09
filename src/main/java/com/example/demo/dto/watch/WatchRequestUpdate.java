package com.example.demo.dto.watch;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class WatchRequestUpdate {
	private int watch_id;
	private String name; // 필수
	private int price;
	private String description;// 필수
	private String category; // 필수
	private String grade;
	private int warranty;
//	private int selling;
	
}
