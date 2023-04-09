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

import com.example.demo.dto.PurchaseRequest;
import com.example.demo.dto.watch.WatchResponse;
import com.example.demo.service.PurchaseService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
	private final PurchaseService purchaseService;

	// 구매목록 추가 -> o
	@PostMapping("/{watch_id}")
	public int insertPurchase(@RequestBody PurchaseRequest request) {
		return purchaseService.insertPurchase(request);
	}

	// 구매목록 가져오기 -> o
	@GetMapping("/list/{member_id}")
	public List<WatchResponse> selectPurchase(@PathVariable int member_id) {
		return purchaseService.findAll(member_id);
	}
	
}
