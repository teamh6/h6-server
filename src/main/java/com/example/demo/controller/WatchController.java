package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.image.ImageRequest;
import com.example.demo.dto.watch.WatchRequestCase;
import com.example.demo.dto.watch.WatchRequestCaseMember;
import com.example.demo.dto.watch.WatchRequestRegister;
import com.example.demo.dto.watch.WatchRequestUpdate;
import com.example.demo.dto.watch.WatchResponse;
import com.example.demo.service.WatchService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/watches")
@RequiredArgsConstructor
public class WatchController {// 이것도 /api -> /watch 이런식으로 나중에 url 변경 필요할 듯

	private final WatchService watchService;

	// 전체 시계리스트를 가져올 때
	@GetMapping("")
	public List<WatchResponse> selectWatchList() {
		return watchService.selectWatchList();
	}

	// 상황에 따른 시계리스트 가져오기 - 관리자
	@PostMapping("/case")
	public List<WatchResponse> selectCaseList(@RequestBody WatchRequestCase request) {
		return watchService.selectCaseList(request);
	}

	// 시계 추가
	@PostMapping("")
	public int insertWatch(@RequestBody WatchRequestRegister request) {
		System.out.println(request);
		return watchService.insertWatch(request);
	}

	// 시계 이미지 추가
	@PostMapping("/{watch_id}")
	public int insertWatch(@RequestBody ImageRequest request) {
		return watchService.insertImage(request);
	}

	// 시계 id를 받아 상세 페이지로 이동할 때
	@GetMapping("/{watch_id}")
	public WatchResponse selectWatch(@PathVariable int watch_id) {

		return watchService.selectWatch(watch_id);
	}

	// 시계 id를 받아 시계 삭제
	@DeleteMapping("/{watch_id}")
	public int deletetWatch(@PathVariable int watch_id) {
		return watchService.deleteWatch(watch_id);
	}

	// 시계 id를 받아 시계 업데이트
	@PutMapping("/{watch_id}")
	public int updateWatch(@RequestBody WatchRequestUpdate request) {
		return watchService.updateWatch(request);
	}

	// 시계 종류와 case를 받아 시계항목 가져오기
	@PostMapping("/category")
	public List<WatchResponse> selectCategory(@RequestBody WatchRequestCase request) {
		return watchService.selectCaseList(request);
	}

	// 회원이 등록한 모든 시계 리스트 가져오기
	@GetMapping("/apply/{member_id}")
	public List<WatchResponse> selectApplyList(@PathVariable("member_id") int member_id) {
		return watchService.selectApplyList(member_id);
	}

	// 회원이 등록한 모든시계 리스트 수 가져오기
	@GetMapping("/apply/count/{member_id}")
	public int selectApplyCount(@PathVariable("member_id") int member_id) {
		return watchService.selectApplyCount(member_id);
	}

	// 상황에 따른 시계 가져오기 - 회원
	@PostMapping("/case/{member_id}")
	public List<WatchResponse> selectCaseListMember(@RequestBody WatchRequestCaseMember request) {
		return watchService.selectCaseListMember(request);
	}

	// 상황에 따른 시계 수 가져오기 - 회원
	@PostMapping("/case/count/{member_id}")
	public int selectWatchCountMember(@RequestBody WatchRequestCaseMember request) {
		return watchService.selectWatchCountMember(request);
	}

	// 상황에 따른 시계 수 가져오기 - 관리자
	@PostMapping("/case/count")
	public int selectWatchCount(@RequestBody WatchRequestCase request) {
		return watchService.selectWatchCount(request);
	}

}
