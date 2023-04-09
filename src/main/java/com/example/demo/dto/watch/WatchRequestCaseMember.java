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
public class WatchRequestCaseMember {
	private int member_id;
	private int warranty;
	private int selling;

	@Override
	public String toString() {

		return "member_id : " + this.member_id + ", warranty : " + this.warranty + ", selling : " + this.selling;
	}
}
