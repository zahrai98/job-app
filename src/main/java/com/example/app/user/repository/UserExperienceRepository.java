package com.example.app.user.repository;

import com.example.app.user.model.UserExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExperienceRepository extends JpaRepository<UserExperienceEntity, Long> {
}
