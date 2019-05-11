package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	
	/*@Query(value="select id from tb_member where email=?1",nativeQuery=true)
	List<Member> forEmailUsers(String email); */
	@Query(value="select id from tb_member where email=?1",nativeQuery=true)
	List<String> usersid(String email);
	List<Member> findByRole(String role);
	boolean existsByIdAndNameAndBirth(String id,String name, String birth);
	Member findByIdAndNameAndBirth(String id,String name, String birth);
}
