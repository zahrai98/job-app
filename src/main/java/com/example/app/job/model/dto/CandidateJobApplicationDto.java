package com.example.app.job.model.dto;

import com.example.app.job.model.JobApplicationEntity;
import com.example.app.job.model.JobPositionEntity;
import com.example.app.job.model.JobSkillsEntity;
import com.example.app.job.model.enums.JobApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateJobApplicationDto {
    private Long id;
    private String title;
    private String description;
    private Integer minExperience;
    private Integer maxExperience;
    private String location;
    private Set<String> requirements;
    private Set<JobSkillsOut> skills;
    private JobApplicationStatus status;

    public CandidateJobApplicationDto(JobPositionEntity jobPositionEntity, JobApplicationStatus status) {
        if (jobPositionEntity != null) {
            this.status = status;
            this.id = jobPositionEntity.getId();
            this.title = jobPositionEntity.getTitle();
            this.description = jobPositionEntity.getDescription();
            this.minExperience = jobPositionEntity.getMinExperience();
            this.maxExperience = jobPositionEntity.getMaxExperience();
            this.location = jobPositionEntity.getLocation();
            this.requirements = jobPositionEntity.getRequirements();
            Set<JobSkillsEntity> allSkills = jobPositionEntity.getSkills();
            if (allSkills != null) {
                this.skills = allSkills.stream().map(JobSkillsOut::new).collect(Collectors.toSet());
            }

        }


    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class JobSkillsOut {
        private Long skillId;
        private String title;

        public JobSkillsOut(JobSkillsEntity jobSkillsEntity) {
            if (jobSkillsEntity != null) {
                this.skillId = jobSkillsEntity.getId();
                this.title = jobSkillsEntity.getTitle();
            }
        }
    }

}
