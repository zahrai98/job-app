package com.example.app.job.model.dto;

import com.example.app.job.model.JobSkillsEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobSkillDtoIn {
    @NotBlank
    @NotNull
    private String title;


    public JobSkillsEntity convertToEntity(JobSkillsEntity jobSkillsEntity) {
        if (jobSkillsEntity == null) {
            jobSkillsEntity = new JobSkillsEntity();
        }
        jobSkillsEntity.setTitle(title);
        return jobSkillsEntity;
    }


}
