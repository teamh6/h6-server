package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.basket.BasketRequest;
import com.example.demo.dto.watch.WatchResponse;
import com.example.demo.repository.BasketRepository;
import com.example.demo.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasketService {
	private final BasketRepository basketRepository;

	private final ImageRepository imageRepository;

	// 장바구니에 시계리스트 가져오기
	public List<WatchResponse> findAll(int member_id) {
		// BasketResponseDto -> WatchResponse 변경
		// basketList는 시계목록을 가져오는 것이므로 -> watchList로 변경
		List<WatchResponse> watchList = new ArrayList<>();

		watchList = basketRepository.selectBasketList(member_id);

		for (WatchResponse watch : watchList) {

			watch.setImg_urls(imageRepository.selectImages(watch.getWatch_id()));
		}

		return watchList;

	}

	// 장바구니에 추가 후 추가된 행의 개수 반환
	public int insertBasket(final BasketRequest request) {
		int result = 0;
		try {
			// 장바구니 중복 체크
			result = basketRepository.checkBasket(request.getMember_id(), request.getWatch_id());
			// 중복된 것이 있을 때
			if (result != 0) {
				// 추가된 행은 0 이므로 0반환
				return 0;
			}
			result = basketRepository.insertBasket(request.getMember_id(), request.getWatch_id());

		} catch (Exception e) {
			System.out.println(e);
			return -9999;
		}
		System.out.println("basket : " + result + "행 들어감");
		return result;
	}

	// 장바구니에 삭제 후 삭제된 행의 개수 반환
	public int deleteBasket(final BasketRequest request) {
		int result = 0;
		try {
			result = basketRepository.deleteBasket(request.getMember_id(), request.getWatch_id());
		} catch (Exception e) {
			System.out.println(e);
			return -9999;
		}
		return result;
	}

	public int checkBasket(final BasketRequest request) {
		int result=0;
		try {
			result = basketRepository.checkBasket(request.getMember_id(), request.getWatch_id());
			
		}catch(Exception e) {
			System.out.println(e);
			return -9999;
		}
		return result;
	}

}
