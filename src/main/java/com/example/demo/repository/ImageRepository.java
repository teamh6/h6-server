package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageRepository {

	// 시계 id를 받아 이미지 경로들을 가져옴
	@Select("select img_url from image where watch_id = #{watch_id}")
	List<String> selectImages(@Param("watch_id") int watch_id);

	@SelectKey(statement = "select count(*) from image where watch_id = #{watch_id} and img_url = #{img_url}", keyProperty = "count", before = true, resultType = int.class)
	@Insert("insert into image(watch_id,img_url) select watch_id,#{img_url} from watch where watch_id = #{watch_id}")
	int insertImage(@Param("watch_id") int watch_id, @Param("img_url") String img_url);
	

}
