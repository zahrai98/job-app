package com.example.app.job.model.dto;


import com.example.app.job.model.JobPositionEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobPositionDtoIn {
    private Long ownerId;
    @NotBlank
    @NotNull
    private String title;
    @NotBlank
    @NotNull
    private String description;
    private Integer minExperience;
    private Integer maxExperience;
    @NotBlank
    @NotNull
    private String location;
    private Set<Long> skills;
    private Set<String> requirements;


    public JobPositionEntity convertToEntity(JobPositionEntity jobPositionEntity) {
        if (jobPositionEntity == null) {
            jobPositionEntity = new JobPositionEntity();
        }
        jobPositionEntity.setTitle(title);
        jobPositionEntity.setDescription(description);
        jobPositionEntity.setMinExperience(minExperience);
        jobPositionEntity.setMaxExperience(maxExperience);
        jobPositionEntity.setLocation(location);
        jobPositionEntity.setRequirements(requirements);
        return jobPositionEntity;
    }


}
