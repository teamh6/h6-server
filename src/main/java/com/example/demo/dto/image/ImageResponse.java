package com.example.demo.dto.image;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ImageResponse {// db로 부터 image table의 속성값을 받을 dto
	private int img_id;
	private int watch_id;
	private String img_url;
}
