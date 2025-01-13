package com.example.app.job.controller;

import com.example.app.common.dto.PageableDto;
import com.example.app.job.model.dto.*;
import com.example.app.job.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/job-application")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping(path = "")
    public ResponseEntity<List<JobApplicationCreateDtoOut>> getAll(
            @Valid PageableDto pageableDto
    ) {
        return ResponseEntity.ok(jobApplicationService.getAll(pageableDto));
    }

    @PostMapping("")
    public ResponseEntity<JobApplicationCreateDtoOut> create(@Valid @RequestBody JobApplicationDtoIn jobApplicationDtoIn) {
        return ResponseEntity.ok(jobApplicationService.create(jobApplicationDtoIn));
    }

    @GetMapping(path = "/{jobApplicationId}")
    public ResponseEntity<JobApplicationCreateDtoOut> getById(@PathVariable(name = "jobApplicationId") Long jobApplicationId) {
        return ResponseEntity.ok(jobApplicationService.getById(jobApplicationId));
    }

    @GetMapping(path = "/{candidateId}/job-applications")
    public ResponseEntity<Set<CandidateJobApplicationDto>> getJobApplicationsByCandidateId(@PathVariable(name = "candidateId") Long candidateId) {
        return ResponseEntity.ok(jobApplicationService.getJobApplicationsOfCandidate(candidateId));
    }

    @GetMapping(path = "/{jobId}/candidates")
    public ResponseEntity<Set<JobCandidateApplicationDto>> getCandidateByJobId(@PathVariable(name = "jobId") Long jobId) {
        return ResponseEntity.ok(jobApplicationService.getCandidatesOfJob(jobId));
    }

}