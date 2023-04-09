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

import com.example.demo.dto.watch.WatchResponse;

@Mapper
@Repository
public interface WatchRepository {

	// 모든 시계들을 가져오는 메서드
	@Select("select * from watch")
	List<WatchResponse> selectWatchList();

	// 시계 id를 받아 시계를 찾아주는 메서드
	@Select("select * from watch where watch_id = #{watch_id}")
	public WatchResponse selectWatch(int watch_id);

	// 시계 id를 받아 좋아요를 1 올려주는 메서드
	@Update("update watch set likes = likes+1 where watch_id = #{watch_id}")
	void upLikes(int watch_id);

	// 시계 id를 받아 좋아요를 1 줄여주는 메서드
	@Update("update watch set likes = likes-1 where watch_id = #{watch_id}")
	void downLikes(int watch_id);

	// 시계 id를 받아 시계 삭제
	@SelectKey(statement = "select count(*) from  watch where watch_id = #{watch_id}", keyProperty = "count", before = true, resultType = int.class)
	@Delete("delete from watch where watch_id = #{watch_id}")
	int deleteWatch(@Param("watch_id") int watch_id);

	// 시계 등록후 들어간 시계의 행의 수를 리턴
	@SelectKey(statement = "select count(*) from watch where name = #{name} and member_id = #{member_id}", keyProperty = "count", before = false, resultType = int.class)
	@Insert("insert into watch(member_id, name, price, description, category) values(#{member_id}, #{name}, #{price}, #{description}, #{category})")
	int insertWatch(@Param("member_id") int member_id, @Param("name") String name, @Param("price") int price,
			@Param("description") String description, @Param("category") String category);

	// 시계 id를 가져오기 -> image에 넣기 위해
	@Select("select watch_id from watch where name = #{name} and member_id = #{member_id}")
	int selectWatchId(@Param("name") String name, @Param("member_id") int member_id);

	// 시계 정보 변경
	@SelectKey(statement = "select count(*) from watch where watch_id = #{watch_id}", keyProperty = "count", before = false, resultType = int.class)
	@Update("update watch set name = #{name}, price = #{price}, description = #{description}, category = #{category}, grade = #{grade},warranty=#{warranty} where watch_id = #{watch_id}")
	int updateWatch(@Param("watch_id") int watch_id, @Param("name") String name, @Param("price") int price,
			@Param("description") String description, @Param("category") String category, @Param("grade") String grade,
			@Param("warranty") int warranty);

	@Select("select * from watch where warranty = #{warranty} and selling = #{selling}")
	List<WatchResponse> selectCaseList(@Param("warranty") int warranty, @Param("selling") int selling);

	// warranrty와 selling을 같이 받아서 update해줌.
	@Update("update watch set selling = 1 where watch_id =#{watch_id}")
	void updateSelling(int watch_id);

	// 종류별과 상황에 따른 시계 가져오기
	@Select("select * from watch where warranty = #{warranty} and selling = #{selling} and category like #{category}")
	List<WatchResponse> selectCategory(@Param("warranty") int warranty, @Param("selling") int selling,
			@Param("category") String category);

	// 회원이 등록한 모든 시계 가져오기
	@Select("select * from watch where member_id = #{member_id}")
	List<WatchResponse> selectApplyList(@Param("member_id") int member_id);

	// 회원이 등록한 시계 수 가져오기
	@Select("select count(*) from watch where member_id = #{member_id}")
	int selectApplyCount(@Param("member_id") int member_id);

	// 상황에 따라 시계리스트 가져오기 - 회원
	@Select("select * from watch where member_id = #{member_id} and warranty = #{warranty} and selling = #{selling}")
	List<WatchResponse> selectCaseListMember(@Param("member_id") int member_id, @Param("warranty") int warranty,
			@Param("selling") int selling);

	// 상황에 따른 시계 수 가져오기 - 회원
	@Select("select count(*) from watch where member_id = #{member_id} and warranty = #{warranty} and selling = #{selling}")
	int selectWatchCountMember(@Param("member_id") int member_id, @Param("warranty") int warranty,
			@Param("selling") int selling);

	// 상황에 따른 시계수 가져오기 - 관리자
	@Select("select count(*)  from watch where warranty = #{warranty} and selling = #{selling}")
	int selectWatchCount(@Param("warranty") int warranty, @Param("selling") int selling);
}
