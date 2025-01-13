package com.example.app.job.repository;

import com.example.app.job.model.JobPositionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPositionEntity, Long>, JpaSpecificationExecutor<JobPositionEntity> {
}
