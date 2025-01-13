package com.example.app.job.model.dto;


import com.example.app.job.model.JobApplicationEntity;
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
public class JobApplicationDtoIn {
    private Long jobId;
    private Long candidateId;


    public JobApplicationEntity convertToEntity(JobApplicationEntity jobApplicationEntity) {
        if (jobApplicationEntity == null) {
            jobApplicationEntity = new JobApplicationEntity();
        }
        return jobApplicationEntity;
    }


}
