package com.example.app.job.repository.projection;

import com.example.app.job.model.JobPositionEntity;
import com.example.app.job.model.enums.JobApplicationStatus;

public interface JobPositionStatusProjection {
    JobPositionEntity getJobPosition();
    JobApplicationStatus getStatus();
}