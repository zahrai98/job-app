package com.example.app.user.repository;

import com.example.app.user.model.CandidateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    @Query("SELECT c FROM candidate c JOIN FETCH c.userExperiences WHERE c.id = :id")
    Optional<CandidateEntity> findByIdWithExperiences(@Param("id") Long id);

    @Query("SELECT c FROM candidate c JOIN FETCH c.userExperiences WHERE c.user.id = :id")
    Optional<CandidateEntity> findByUserIdWithExperiences(@Param("id") Long id);

//    @Query(value = "SELECT c FROM candidate c JOIN FETCH c.userExperiences",
//            countQuery = "SELECT count(c) FROM candidate c")
//    Page<CandidateEntity> findAllWithExperiences(Pageable pageable);

}
