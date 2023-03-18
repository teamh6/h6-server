package com.example.demo.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.MemberResponseDto;
import com.example.demo.model.Member;

@Mapper
@Repository
public interface MemberRepository {

	@Select("select * from member where email = #{email} and password = #{password}")
	MemberResponseDto selectMember(@Param("email") String email,@Param("password") String password);

	MemberResponseDto selectXml(Map<String, String> h1, Map<String, String> h2);

}
