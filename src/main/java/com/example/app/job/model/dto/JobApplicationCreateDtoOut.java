package com.example.app.job.model.dto;

import com.example.app.job.model.JobApplicationEntity;
import com.example.app.job.model.JobPositionEntity;
import com.example.app.job.model.JobSkillsEntity;
import com.example.app.job.model.enums.JobApplicationStatus;
import com.example.app.user.model.CandidateEntity;
import com.example.app.user.model.UserEntity;
import com.example.app.user.model.UserExperienceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationCreateDtoOut {
    private Long id;
    private CandidateOut candidate;
    private JobPositionOut jobPosition;
    private JobApplicationStatus status;

    public JobApplicationCreateDtoOut(JobApplicationEntity jobApplicationEntity) {
        if (jobApplicationEntity != null) {
            this.id = jobApplicationEntity.getId();
            this.candidate = new CandidateOut(jobApplicationEntity.getCandidate());
            this.jobPosition = new JobPositionOut(jobApplicationEntity.getJobPosition());
            this.status = jobApplicationEntity.getStatus();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class CandidateOut {
        private Long id;
        private String firstName;
        private String lastName;
        private UserOut user;
        private Set<JobSkillsOut> skills;
        private File resume;
        private Set<UserExperienceOut> experiences;

        public CandidateOut(CandidateEntity candidateEntity) {
            if (candidateEntity != null) {
                this.id = candidateEntity.getId();
                this.firstName = candidateEntity.getFirstName();
                this.lastName = candidateEntity.getLastName();
                this.user = new UserOut(candidateEntity.getUser());
                Set<JobSkillsEntity> allSkills = candidateEntity.getSkills();
                if (allSkills != null) {
                    this.skills = allSkills.stream().map(JobSkillsOut::new).collect(Collectors.toSet());
                }
                Set<UserExperienceEntity> all_experience = candidateEntity.getUserExperiences();
                if (all_experience != null) {
                    this.experiences = all_experience.stream().map(UserExperienceOut::new).collect(Collectors.toSet());
                }
            }
        }
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class JobPositionOut {
        private Long id;
        private String title;
        private String description;
        private Integer minExperience;
        private Integer maxExperience;
        private String location;
        private Set<String> requirements;
        private Set<JobSkillsOut> skills;

        public JobPositionOut(JobPositionEntity jobPositionEntity) {
            if (jobPositionEntity != null) {
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
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserOut {
        private Long userId;
        private String username;
        private String email;

        public UserOut(UserEntity userEntity) {
            if (userEntity != null) {
                this.userId = userEntity.getId();
                this.username = userEntity.getUsername();
                this.email = userEntity.getEmail();
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

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserExperienceOut {
        private Long experienceId;
        private String company;
        private String jobTitle;
        private LocalDate startDate;
        private LocalDate endDate;

        public UserExperienceOut(UserExperienceEntity userExperienceEntity) {
            if (userExperienceEntity != null) {
                this.experienceId = userExperienceEntity.getId();
                this.company = userExperienceEntity.getCompany();
                this.jobTitle = userExperienceEntity.getJobTitle();
                this.startDate = userExperienceEntity.getStartDate();
                this.endDate = userExperienceEntity.getEndDate();
            }
        }
    }


}

