package com.example.app.job.service;

import com.example.app.common.dto.PageableDto;
import com.example.app.configs.exceptions.SystemException;
import com.example.app.job.model.JobApplicationEntity;
import com.example.app.job.model.JobPositionEntity;
import com.example.app.job.model.dto.*;
import com.example.app.job.repository.JobApplicationRepository;
import com.example.app.job.repository.JobPositionRepository;
import com.example.app.job.repository.projection.CandidateJobApplicationStatusProjection;
import com.example.app.job.repository.projection.JobPositionStatusProjection;
import com.example.app.user.model.CandidateEntity;
import com.example.app.user.repository.CandidateRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobApplicationService {
    private final JobPositionRepository jobPositionRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final CandidateRepository candidateRepository;

    public JobApplicationService(JobPositionRepository jobPositionRepository, JobApplicationRepository jobApplicationRepository, CandidateRepository candidateRepository) {
        this.jobPositionRepository = jobPositionRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.candidateRepository = candidateRepository;
    }

    public JobApplicationCreateDtoOut create(JobApplicationDtoIn jobApplicationDtoIn) {
        CandidateEntity candidateEntity = candidateRepository.findById(jobApplicationDtoIn.getCandidateId()).orElseThrow(
                () -> new SystemException(HttpStatus.NOT_FOUND, "candidate not found ", 404));

        JobPositionEntity jobPositionEntity = jobPositionRepository.findById(jobApplicationDtoIn.getJobId()).orElseThrow(
                () -> new SystemException(HttpStatus.NOT_FOUND, "job not found ", 404));

        JobApplicationEntity jobApplicationEntity = jobApplicationDtoIn.convertToEntity(null);
        jobApplicationEntity.setJobPosition(jobPositionEntity);
        jobApplicationEntity.setCandidate(candidateEntity);
        jobApplicationRepository.save(jobApplicationEntity);
        return new JobApplicationCreateDtoOut(jobApplicationEntity);
    }

    public Set<CandidateJobApplicationDto> getJobApplicationsOfCandidate(Long candidateId) {
        Set<JobPositionStatusProjection> jobPositionsAndStatusByCandidateId =
                jobApplicationRepository.findJobPositionsAndStatusByCandidateId(candidateId);
        return jobPositionsAndStatusByCandidateId.stream().map(data ->
                new CandidateJobApplicationDto(data.getJobPosition(), data.getStatus())).collect(Collectors.toSet());
    }

    public Set<JobCandidateApplicationDto> getCandidatesOfJob(Long jobPositionId) {
        Set<CandidateJobApplicationStatusProjection> candidateAndStatusByJobPositionId =
                jobApplicationRepository.findCandidateAndStatusByJobPositionId(jobPositionId);
        return candidateAndStatusByJobPositionId.stream().map(data ->
                new JobCandidateApplicationDto(data.getCandidate(), data.getStatus())).collect(Collectors.toSet());
    }

    public JobApplicationCreateDtoOut getById(Long id) {
        return new JobApplicationCreateDtoOut(jobApplicationRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "job not found", 404)));
    }

    public List<JobApplicationCreateDtoOut> getAll(PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getPage() - 1, pageableDto.getSize());
        return jobApplicationRepository.findAll(pageable).stream().map(JobApplicationCreateDtoOut::new).collect(Collectors.toList());
    }
}


