package com.example.app.job.repository;

import com.example.app.job.model.JobApplicationEntity;
import com.example.app.job.repository.projection.CandidateJobApplicationStatusProjection;
import com.example.app.job.repository.projection.JobPositionStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Long> {

    @Query("SELECT ja.jobPosition AS jobPosition, ja.status AS status " +
            "FROM JobApplicationEntity ja WHERE ja.candidate.id = :candidateId")
    Set<JobPositionStatusProjection> findJobPositionsAndStatusByCandidateId(Long candidateId);

    @Query("SELECT ja.candidate AS candidate, ja.status AS status " +
            "FROM JobApplicationEntity ja WHERE ja.jobPosition.id = :jobId")
    Set<CandidateJobApplicationStatusProjection> findCandidateAndStatusByJobPositionId(Long jobId);
}
