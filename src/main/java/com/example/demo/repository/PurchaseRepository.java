package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.PurchaseRequest;
import com.example.demo.dto.watch.WatchResponse;

@Mapper
@Repository
public interface PurchaseRepository {
	
	// 구매목록에 시계 추가하기
	@SelectKey(statement = "select count(*) from purchase where member_id=#{member_id} and watch_id=#{watch_id}", keyProperty = "count", before = false, resultType = int.class)
	@Insert("insert into purchase(member_id , watch_id) values(#{member_id}, #{watch_id})")
	int insertPurchase(@Param("member_id") int member_id, @Param("watch_id") int watch_id);
	
	// 구매목록에 시계 삭제하기
	@SelectKey(statement = "select count(*) from purchase where member_id=#{member_id} and watch_id=#{watch_id", keyProperty = "count", before = true, resultType = int.class)
	@Delete("delete from purchase where member_id = #{member_id} and watch_id = #{watch_id}")
	int deletePurchase(@Param("member_id") int member_id, @Param("watch_id") int watch_id);
	
	// 구매목록에 시계리스트 가져오기 ?
	@Select("select W.* from member M, watch W \r\n"
			+ "where M.member_id in (select member_id from purchase) and \r\n"
			+ " 	M.member_id =#{member_id} and \r\n"
			+ " 	W.watch_id in (select watch_id from purchase where member_id = M.member_id")
	List<WatchResponse> takePurchaseList(@Param("member_id") int member_id);
	
	// 구매목록 조회
	@Select("select W.* from member M, watch W \r\n"
			+ "where M.member_id in (select member_id from purchase) and \r\n"
			+ " 	M.member_id =#{member_id} and \r\n"
			+ " 	W.watch_id in (select watch_id from purchase where member_id = M.member_id)")
	List<WatchResponse> selectPurchaseList(@Param("member_id") int member_id);
	
	// 구매목록 중복확인
	@Select("select count(*) from purchase where member_id = #{member_id} and watch_id = #{watch_id}")
	int checkPurchase(@Param("member_id") int member_id, @Param("watch_id") int watch_id);
	

	
	
}
