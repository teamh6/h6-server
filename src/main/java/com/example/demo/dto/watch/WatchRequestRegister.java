package com.example.demo.dto.watch;



import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class WatchRequestRegister {

	private int member_id;
	private String name;
	private int price;
	private String description;
	private String category;
	private List<String> img_urls;
}
