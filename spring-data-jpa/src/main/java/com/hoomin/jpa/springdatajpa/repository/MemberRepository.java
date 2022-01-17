package com.hoomin.jpa.springdatajpa.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hoomin.jpa.springdatajpa.dto.MemberDto;
import com.hoomin.jpa.springdatajpa.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
	List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

	@Query(name = "Member.findByUsername")
	List<Member> findByUsername(@Param("username") String username);

	List<Member> findTop3HelloBy();

	@Query("select m.username from Member m")
	List<String> findUserNameList();

	@Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) " +
		"from Member m join m.team t")
	List<MemberDto> findMemberDto();

	@Query("select m from Member m where m.username in :names")
	List<Member> findByNames(@Param("names") Collection<String> names);
}