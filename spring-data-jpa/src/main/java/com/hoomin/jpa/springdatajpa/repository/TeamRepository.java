package com.hoomin.jpa.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoomin.jpa.springdatajpa.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}