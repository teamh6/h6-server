package com.example.demo.dto.watch;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class WatchRequestCase {

	private int warranty;
	private int selling;
	private String category;

	@Override
	public String toString() {

		return "warranty : " + this.warranty + ", selling : " + this.selling + ", category : " + category;
	}
}
