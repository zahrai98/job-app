package com.example.app.job.controller;

import com.example.app.common.dto.PageableDto;
import com.example.app.job.model.dto.JobPositionDtoIn;
import com.example.app.job.model.dto.JobPositionDtoOut;
import com.example.app.job.service.JobPositionService;
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
            @Valid PageableDto pageableDto
    ) {
        return ResponseEntity.ok(jobPositionService.getAll(pageableDto));
    }

    @PostMapping("")
    public ResponseEntity<JobPositionDtoOut> create(@Valid @RequestBody JobPositionDtoIn jobPositionDtoIn) {
        return ResponseEntity.ok(jobPositionService.create(jobPositionDtoIn));
    }

    @GetMapping(path = "/{jobId}")
    public ResponseEntity<JobPositionDtoOut> getById(@PathVariable(name = "jobId") Long jobId) {
        return ResponseEntity.ok(jobPositionService.getById(jobId));
    }
}