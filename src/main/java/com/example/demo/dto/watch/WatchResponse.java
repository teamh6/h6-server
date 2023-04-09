package com.example.demo.dto.watch;

import java.time.LocalDateTime;
import java.util.List;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class WatchResponse { // 시계 table의 속성값과 img_url들을 담을 dto

	private int watch_id;
	private int member_id;// 필
	private String name;// 필
	private int price;
	private String description;
	private String category;
	private LocalDateTime register_date;
	private int warranty;
	private int selling;
	private int likes;
	private String grade;
	// 시계 url들을 담아줄 List
	private List<String> img_urls;
}
