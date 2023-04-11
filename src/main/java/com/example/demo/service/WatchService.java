package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.image.ImageRequest;
import com.example.demo.dto.watch.WatchRequestCase;
import com.example.demo.dto.watch.WatchRequestCaseMember;
import com.example.demo.dto.watch.WatchRequestRegister;
import com.example.demo.dto.watch.WatchRequestUpdate;
import com.example.demo.dto.watch.WatchResponse;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.WatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WatchService {
	// 시계 정보를 가져올 repository
	private final WatchRepository watchRepository;

	// 시계 img_url을 가져올 repository
	private final ImageRepository imageRepository;

	public List<WatchResponse> selectWatchList() {
		List<WatchResponse> watchList = new ArrayList<>();

		watchList = watchRepository.selectWatchList();

		for (WatchResponse watch : watchList) {

			watch.setImg_urls(imageRepository.selectImages(watch.getWatch_id()));

		}

		return watchList;

	}

	// 시계 id를 받아서 시계를 받아 리턴
	public WatchResponse selectWatch(int watch_id) {
		WatchResponse watchResponseDto = watchRepository.selectWatch(watch_id);
		watchResponseDto.setImg_urls(imageRepository.selectImages(watchResponseDto.getWatch_id()));
		return watchResponseDto;
	}

	// 시계 생성후 생성된 행의 개수 반환
	public int insertWatch(final WatchRequestRegister request) {
		int result = 0;
		try {
			// 시계를 넣고
			result = watchRepository.insertWatch(request.getMember_id(), request.getName(), request.getPrice(),
					request.getDescription(), request.getCategory());
			// 넣은 시계의 watch_id 가져와서
			int watch_id = watchRepository.selectWatchId(request.getName(), request.getMember_id());

			// image테이블에 watch_id와 img_url을 넣는다.
			for (String img_url : request.getImg_urls()) {
				imageRepository.insertImage(watch_id, img_url);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return -9999;
		}
		System.out.println("signUp : " + result + "행 들어감");
		return result;
	}

	public int insertImage(final ImageRequest request) {
		int result = 0;
		try {
			for (String img_url : request.getImg_urls()) {
				imageRepository.insertImage(request.getWatch_id(), img_url);
				result++;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return -9999;
		}
		System.out.println("insertImage : " + result + "행 들어감");
		return result;
	}

	// 시계 id를 받아서 시계를 삭제 후 삭제 된 행 반환
	public int deleteWatch(int watch_id) {
		int result = 0;
		try {

			// 삭제된 행
			result = watchRepository.deleteWatch(watch_id);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return -9999;
		}
		System.out.println("deleteWatch : " + result + "행 삭제됨");
		return result;
	}

	// 시계id 받아서 시계 정보 업데이트
	public int updateWatch(final WatchRequestUpdate request) {
		int result = 0;
		try {
			// 업데이트 된 행
			result = watchRepository.updateWatch(request.getWatch_id(), request.getName(), request.getPrice(),
					request.getDescription(), request.getCategory(), request.getGrade(),request.getWarranty());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return -9999;
		}
		System.out.println("updateWatch : " + result + "행 업데이트 됨");
		return result;
	}

	// 시계 id를 받아 좋아요를 1 업해줌
	public void upLikes(int watch_id) {
		watchRepository.upLikes(watch_id);
	}

	// 매개 변수에 따라 가져오는 리스트가 달라짐.
	public List<WatchResponse> selectCaseList(final WatchRequestCase request) {
		List<WatchResponse> watchList = null;

		if (request.getCategory().equals("all") || request.getCategory() == null) {
			watchList = watchRepository.selectCaseList(request.getWarranty(), request.getSelling());

		} else {
			watchList = watchRepository.selectCategory(request.getWarranty(), request.getSelling(),
					request.getCategory());
		}
		for (WatchResponse watch : watchList) {

			watch.setImg_urls(imageRepository.selectImages(watch.getWatch_id()));

		}
		return watchList;
	}

//	// 시계 종류별로 가져오기
//	public List<WatchResponse> selectCategory(String category) {
//		List<WatchResponse> watchList = watchRepository.selectCategory(category);
//		for (WatchResponse watch : watchList) {
//			watch.setImg_urls(imageRepository.selectImages(watch.getWatch_id()));
//		}
//		return watchList;
//	}

	// 회원이 등록한 시계 가져오기
	public List<WatchResponse> selectApplyList(int member_id) {
		List<WatchResponse> watchList = watchRepository.selectApplyList(member_id);
		for (WatchResponse watch : watchList) {
			watch.setImg_urls(imageRepository.selectImages(watch.getWatch_id()));
		}
		return watchList;
	}

	// * 상황에 따라 시계리스트를 가져오는 메서드 - 관리자
	// 판매중 : warranty = 2 and selling = 0
	// 판매완료 : warranty = 2 and selling = 1
	// 검증중 : warranty = 1 and selling = 0
	// 판매불가 : warranty = - 1 and selling = 0
	// 회원이 등록한 시계 가져오기 -> case 별로
	public List<WatchResponse> selectCaseListMember(WatchRequestCaseMember request) {
		List<WatchResponse> watchList = new ArrayList<>();
		// selling이 4면 -1,2,3 다가져옴.
		if (request.getSelling() == 4) {
			for (int i = 0; i < request.getSelling(); i++) {
				watchList
						.addAll(watchRepository.selectCaseListMember(request.getMember_id(), request.getWarranty(), i));
			}
		} else {
			watchList = watchRepository.selectCaseListMember(request.getMember_id(), request.getWarranty(),
					request.getSelling());
		}

		for (WatchResponse watch : watchList) {
			watch.setImg_urls(imageRepository.selectImages(watch.getWatch_id()));
		}
		return watchList;
	}

	// 상황에 따른 시계 수를 가져오기 - 회원
	public int selectWatchCountMember(WatchRequestCaseMember request) {
		int count = 0;
		if (request.getSelling() == 4) {
			for (int i = 0; i < request.getSelling(); i++) {
				count += watchRepository.selectWatchCountMember(request.getMember_id(), request.getWarranty(), i);
			}
		} else {
			count = watchRepository.selectWatchCountMember(request.getMember_id(), request.getWarranty(),
					request.getSelling());
		}
		System.out.println(request.toString() + ", count : " + count);
		return count;
	}

	// 상황에 따른 시계 수를 가져오기 - 관리자
	public int selectWatchCount(WatchRequestCase request) {
		int count = 0;
		count = watchRepository.selectWatchCount(request.getWarranty(), request.getSelling());
		return count;
	}

	// 회원이 등록한 모든 시계 수 가져오기
	public int selectApplyCount(int member_id) {

		return watchRepository.selectApplyCount(member_id);
	}

}
