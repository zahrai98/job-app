package com.example.app.job.repository.projection;

import com.example.app.job.model.JobPositionEntity;
import com.example.app.job.model.enums.JobApplicationStatus;
import com.example.app.user.model.CandidateEntity;

public interface CandidateJobApplicationStatusProjection {
    CandidateEntity getCandidate();
    JobApplicationStatus getStatus();
}