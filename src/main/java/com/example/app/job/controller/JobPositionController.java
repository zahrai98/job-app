package com.example.app.job.controller;

import com.example.app.common.dto.PageableDto;
import com.example.app.job.model.dto.JobPositionDtoIn;
import com.example.app.job.model.dto.JobPositionDtoInEdit;
import com.example.app.job.model.dto.JobPositionDtoOut;
import com.example.app.job.model.dto.JobPositionFilter;
import com.example.app.job.service.JobPositionService;
import com.example.app.user.model.dto.CandidateInEdit;
import com.example.app.user.model.dto.CandidateOut;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/job-position")
public class JobPositionController {

    private final JobPositionService jobPositionService;

    @GetMapping(path = "")
    public ResponseEntity<List<JobPositionDtoOut>> getAll(
            @Valid PageableDto pageableDto,
            @ModelAttribute JobPositionFilter jobPositionFilter
    ) {
        return ResponseEntity.ok(jobPositionService.getAll(pageableDto, jobPositionFilter));
    }

    @PostMapping("")
    public ResponseEntity<JobPositionDtoOut> create(@Valid @RequestBody JobPositionDtoIn jobPositionDtoIn) {
        return ResponseEntity.ok(jobPositionService.create(jobPositionDtoIn));
    }

    @GetMapping(path = "/{jobId}")
    public ResponseEntity<JobPositionDtoOut> getById(@PathVariable(name = "jobId") Long jobId) {
        return ResponseEntity.ok(jobPositionService.getById(jobId));
    }


    @PutMapping(value = "{jobId}")
    public ResponseEntity<JobPositionDtoOut> update(@PathVariable("jobId") Long jobId,
                                                    @RequestBody @Valid JobPositionDtoInEdit jobPositionDtoInEdit) {
        return ResponseEntity.ok(jobPositionService.update(jobId, jobPositionDtoInEdit));
    }

    @DeleteMapping(value = "{jobId}")
    public void delete(@PathVariable("jobId") Long jobId) {
        jobPositionService.delete(jobId);
    }
}