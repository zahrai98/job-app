package com.example.app.user.model.dto;


import com.example.app.user.model.CandidateEntity;
import com.example.app.user.model.UserExperienceEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateInEdit {
    @NotBlank
    @NotNull
    private String firstName;
    @NotBlank
    @NotNull
    private String lastName; // TODO validation
    private byte[] resume;
    // TODO validation  send email
    private Set<Long> skills;
    private Set<UserExperienceIn> experiences;

    public CandidateEntity convertToEntity(CandidateEntity candidateEntity) {
        if (candidateEntity == null) {
            candidateEntity = new CandidateEntity();
        }
        candidateEntity.setFirstName(firstName);
        candidateEntity.setLastName(lastName);
        candidateEntity.setResume(resume);

        CandidateEntity finalCandidate = candidateEntity;
        candidateEntity.setUserExperiences(experiences.stream().map(
                experience -> experience.convertToEntity(null, finalCandidate)).collect(Collectors.toSet()));

        return candidateEntity;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserExperienceIn {
        @NotBlank
        @NotNull
        private String company;
        @NotBlank
        @NotNull
        private String jobTitle;
        @NotBlank
        @NotNull
        private LocalDate startDate;
        private LocalDate endDate;

        public UserExperienceEntity convertToEntity(UserExperienceEntity userExperienceEntity, CandidateEntity candidateEntity) {
            if (userExperienceEntity == null) {
                userExperienceEntity = new UserExperienceEntity();
            }
            userExperienceEntity.setCompany(this.company);
            userExperienceEntity.setJobTitle(this.jobTitle);
            userExperienceEntity.setStartDate(this.startDate);
            userExperienceEntity.setEndDate(this.endDate);
            userExperienceEntity.setCandidate(candidateEntity);
            return userExperienceEntity;
        }
    }
}
