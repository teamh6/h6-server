package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.watch.WatchResponse;

@Mapper
@Repository
public interface PickRepository {
	
	

	
	// 찜목록에 시계 추가하기 -> @SelectKey를 추가하여 insert 후에 들어갔는지 행의 수를 반환, 정상적으로 들어가면 1 반환,
	// 그렇지 않으면 0 반환
	
	@SelectKey(statement = "select count(*) from pick where member_id = #{member_id} and watch_id = #{watch_id}", keyProperty = "count", before = false, resultType = int.class)
	@Insert("insert into pick (member_id,watch_id) values(#{member_id},#{watch_id})")
	int insertPick(@Param("member_id") int member_id, @Param("watch_id") int watch_id);

	
	
	@SelectKey(statement = "select count(*) from pick where member_id = #{member_id} and watch_id=#{watch_id}", keyProperty = "count", before = true, resultType = int.class)
	@Delete("delete from pick where member_id = #{member_id} and watch_id = #{watch_id}")
	int deletePick(@Param("member_id") int member_id, @Param("watch_id") int watch_id);

	// 찜목록 시계리스트 가져오기 (member_id)
	@Select("select W.* from member M, watch W \r\n" + " where M.member_id in (select member_id from pick) and \r\n"
			+ "		M.member_id = #{member_id} and \r\n"
			+ " 	W.watch_id in (select watch_id from pick where member_id = M.member_id)")
	List<WatchResponse> selectPickList(@Param("member_id") int member_id);

	// 찜목록 중복체크
	@Select("select count(*) from pick where member_id = #{member_id} and watch_id = #{watch_id}")
	int checkPick(@Param("member_id") int member_id, @Param("watch_id") int watch_id);
}
