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

import com.example.demo.dto.PickRequest;
import com.example.demo.dto.watch.WatchResponse;
import com.example.demo.service.PickService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/pick")
@RequiredArgsConstructor
public class PickController {

	private final PickService pickService;

	// 찜목록 가져오기 -> member_id를 매개변수로 받음
	@GetMapping("/{member_id}") // 찜목록 조회 : get
	public List<WatchResponse> getPicks(@PathVariable int member_id) {
		System.out.println("member_id : " + member_id);
		// 찜목록 리스트 -> 시계리스트 가져옴
		return pickService.findAll(member_id);
	}

	@PostMapping("/{watch_id}")
	public int insertPick(@RequestBody PickRequest request) {
		return pickService.insertPick(request);
	}

	@DeleteMapping("/{watch_id}")
	public int deletePick(@RequestBody PickRequest request) {
		return pickService.deletePick(request);
	}

	@PostMapping("/check/{watch_id}")
	public int checkPick(@RequestBody PickRequest request) {

		System.out.println("request : " + request);
		return pickService.checkPick(request);
	}

}
