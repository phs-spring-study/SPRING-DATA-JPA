package study.datajpa.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import study.datajpa.entity.Member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

	private final EntityManager em;

	List<Member> findAllMembers() {
		return em.createQuery("select m from Member m")
			.getResultList();
	}
}
