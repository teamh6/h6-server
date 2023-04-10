package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PurchaseRequest;
import com.example.demo.dto.watch.WatchResponse;
import com.example.demo.repository.BasketRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.WatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
	private final PurchaseRepository purchaseRepository;
	private final ImageRepository imageRepository;
	private final WatchRepository watchRepository;
	private final BasketRepository basketRepository;

	// 구매목록 추가
	public int insertPurchase(final PurchaseRequest request) {
		int result = 0;
		try {
			// 중복체크 구매목록
			result = purchaseRepository.insertPurchase(request.getMember_id(), request.getWatch_id());
			// watchRepository의 update 사용
			watchRepository.updateSelling(request.getWatch_id());
			basketRepository.deleteBasket(request.getMember_id(), request.getWatch_id());
		} catch (Exception e) {
			System.out.println(e);
			return -9999;
		}
		System.out.println("purchase :" + result + "행 들어감");
		return result;
	}

	// 구매목록 조회하기
	public List<WatchResponse> findAll(int member_id) {
		List<WatchResponse> purchaseList = new ArrayList<>();

		purchaseList = purchaseRepository.selectPurchaseList(member_id);
		for (WatchResponse watch : purchaseList) {
			watch.setImg_urls(imageRepository.selectImages(watch.getWatch_id()));
		}

		return purchaseList;
	}

	// 구매목록에서 시계리스트 가져오기-> 완
	public List<WatchResponse> takeList(int member_id) {
		List<WatchResponse> watchList = new ArrayList<>();

		watchList = purchaseRepository.takePurchaseList(member_id);

		for (WatchResponse watch : watchList) {
			watch.setImg_urls(imageRepository.selectImages(watch.getWatch_id()));
		}

		return watchList;
	}

}
