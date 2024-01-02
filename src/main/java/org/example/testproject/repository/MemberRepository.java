package org.example.testproject.repository;

import org.example.testproject.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = {"roleset"},type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.purview = :social and m.id = :id")
    Optional<Member> findByEmail(String id, Boolean social);
}
