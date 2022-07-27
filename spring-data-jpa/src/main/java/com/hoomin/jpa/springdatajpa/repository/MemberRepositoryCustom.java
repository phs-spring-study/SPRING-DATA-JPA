package com.hoomin.jpa.springdatajpa.repository;

import java.util.List;

import com.hoomin.jpa.springdatajpa.entity.Member;

public interface MemberRepositoryCustom {
	List<Member> findMemberCustom();
}
