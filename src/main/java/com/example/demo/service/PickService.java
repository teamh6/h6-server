package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PickRequest;
import com.example.demo.dto.watch.WatchResponse;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.PickRepository;
import com.example.demo.repository.WatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PickService {
	private final PickRepository pickRepository;
	private final WatchRepository watchRepository;
	private final ImageRepository imageRepository;

	// 찜목록 확인 -> 하트 버튼을 활성화 시키기 위해
	public int checkPick(final PickRequest request) {
		return pickRepository.checkPick(request.getMember_id(), request.getWatch_id());
	}

	// 찜목록에 시계리스트 가져오기
	public List<WatchResponse> findAll(int member_id) {
		List<WatchResponse> watchList = new ArrayList<>();

		watchList = pickRepository.selectPickList(member_id);

		for (WatchResponse watch : watchList) {
			watch.setImg_urls(imageRepository.selectImages(watch.getWatch_id()));
		}

		return watchList;
	}

	// 찜목록 추가
	public int insertPick(final PickRequest request) {
		int result = 0;
		try {

			watchRepository.upLikes(request.getWatch_id());
			result = pickRepository.insertPick(request.getMember_id(), request.getWatch_id());

		} catch (Exception e) {
			System.out.println(e);
			return -9999;
		}
		System.out.println("pick : " + result + "행 들어감.");
		return result;
	}

	// 찜 목록 제거
	public int deletePick(final PickRequest request) {
		int result = 0;
		try {
			// 좋아요가 음수가 되는 걸 막아주기 위해
			if (watchRepository.selectWatch(request.getWatch_id()).getLikes() > 0) {
				watchRepository.downLikes(request.getWatch_id());
			}
			result = pickRepository.deletePick(request.getMember_id(), request.getWatch_id());

		} catch (Exception e) {

			System.out.println(e);
			return -9999;
		}
		System.out.println("pick : " + result + "행 삭제됨.");
		return result;
	}
}
