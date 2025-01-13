package com.example.app.user.model.dto;

import com.example.app.job.model.JobSkillsEntity;
import com.example.app.user.model.CandidateEntity;
import com.example.app.user.model.UserEntity;
import com.example.app.user.model.UserExperienceEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

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