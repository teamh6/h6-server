package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.basket.BasketRequest;
import com.example.demo.dto.watch.WatchResponse;
import com.example.demo.service.BasketService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
	private final BasketService basketService;

	// 장바구니 가져오기 -> member_id를 매개변수로 받아야 함.
	@GetMapping("/{member_id}") // 장바구니 목록 조회 : post -> get
	public List<WatchResponse> getBasketes(@PathVariable int member_id) {
		// 장바구니 리스트 -> 시계리스트를 가져오는 것 : BasketResponseDto -> WatchResponse로 변경
		return basketService.findAll(member_id);
	}

	// (new) 장바구니 추가 -> request로 member_id와 watch_id를 받아와서 장바구니 추가
	@PostMapping("/{watch_id}")
	public int insertBasket(@RequestBody BasketRequest request) {
		
		return basketService.insertBasket(request);
	}
	
	@DeleteMapping("/{watch_id}")
	public int deleteBasket(@RequestBody BasketRequest request) {
		return basketService.deleteBasket(request);
	}
	@PostMapping("/check/{watch_id}")
	public int checkBasket(@RequestBody BasketRequest request) {
		System.out.println("request : " + request);
		return basketService.checkBasket(request);
	}
	
	
//	// 가격
//	@PostMapping("/basket/price")
//	public List<BasketResponseDto> getPrice() {
//		return basketService.getPrice();
//	}
//
//	// 보증서
//	@PostMapping("/basket/warranty")
//	public List<BasketResponseDto> getWarranty() {
//		return basketService.getWarranty();
//	}
//
//	// 등급
//	@PostMapping("/bakset/grade")
//	public List<BasketResponseDto> getGrade() {
//		return basketService.getGrade();
//	}
//
//	// 상세정보
//	@PostMapping("/basket/description")
//	public List<BasketResponseDto> getDescription() {
//		return basketService.getDescription();
//	}

}
