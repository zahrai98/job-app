package com.example.app.job.repository;


import com.example.app.job.model.JobPositionEntity;
import com.example.app.job.model.dto.JobPositionFilter;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class JobPositionSpecification {
    public static Specification<JobPositionEntity> search(JobPositionFilter jobPositionFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (jobPositionFilter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), jobPositionFilter.getId()));
            }
            if (jobPositionFilter.getLocation() != null) {
                predicates.add(criteriaBuilder.equal(root.get("location"), jobPositionFilter.getLocation()));
            }
            if (jobPositionFilter.getTitle() != null) {
                predicates.add(criteriaBuilder.equal(root.get("title"), "%" + jobPositionFilter.getTitle() + "%"));
            }
            if (jobPositionFilter.getDescription() != null) {
                predicates.add(criteriaBuilder.equal(root.get("description"), "%" + jobPositionFilter.getDescription() + "%"));
            }
            if (jobPositionFilter.getMinExperience() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minExperience"), jobPositionFilter.getMinExperience()));
            }
            if (jobPositionFilter.getMaxExperience() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxExperience"), jobPositionFilter.getMaxExperience()));
            }


            if (jobPositionFilter.getSkillId() != null) {
                var skillsJoin = root.join("skills", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(skillsJoin.get("id"), jobPositionFilter.getSkillId()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
//    public static Specification<JobPositionEntity> findBySkillId(Long skillId) {
//        return (root, query, criteriaBuilder) -> {
//            query.distinct(true);
//            var skillsJoin = root.join("skills", JoinType.INNER);
//            return criteriaBuilder.equal(skillsJoin.get("id"), skillId);
//        };
//    }
}