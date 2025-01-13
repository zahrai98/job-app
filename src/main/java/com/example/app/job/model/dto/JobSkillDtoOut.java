package com.example.app.job.model.dto;

import com.example.app.job.model.JobSkillsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSkillDtoOut {
    private Long id;
    private String title;

    public JobSkillDtoOut(JobSkillsEntity jobSkillsEntity) {
        if (jobSkillsEntity != null) {
            this.id = jobSkillsEntity.getId();
            this.title = jobSkillsEntity.getTitle();

        }
    }

}
