package com.example.app.job.repository;

import com.example.app.job.model.JobSkillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JobSkillsRepository extends JpaRepository<JobSkillsEntity, Long> {

    @Query("SELECT j FROM JobSkillsEntity j WHERE j.id IN :ids")
    Set<JobSkillsEntity> findByIds(@Param("ids") Set<Long> ids);
}
