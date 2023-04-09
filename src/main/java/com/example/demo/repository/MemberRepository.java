package com.example.demo.repository;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.member.MemberResponse;

@Mapper
@Repository
public interface MemberRepository {

	// 로그인 시도 시 member를 찾아주는 메서드
	@Select("select member_id, email, name, phone_number, register_date, admin from member where email = #{email} and password = #{password}")
	MemberResponse selectMember(@Param("email") String email, @Param("password") String password);

	// 아이디중복 체크를 위한 메서드
	@Select("select count(*) from member where email = #{email}")
	int selectMemberEmail(@Param("email") String email);

	@SelectKey(statement = "select count(*) from member where email = #{email}", keyProperty = "email", before = false, resultType = int.class)
	@Insert("insert into member(email,password,name,phone_number) values(#{email},#{password},#{name},#{phone_number})")
	int signUp(@Param("email") String email, @Param("password") String password, @Param("name") String name,
			@Param("phone_number") String phone_number);

}
