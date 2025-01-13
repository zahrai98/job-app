package com.example.app.job.controller;

import com.example.app.common.dto.PageableDto;
import com.example.app.job.model.dto.JobSkillDtoIn;
import com.example.app.job.model.dto.JobSkillDtoOut;
import com.example.app.job.service.JobSkillService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/job-skill")
public class JobSkillController {

    private final JobSkillService jobSkillService;

    @GetMapping(path = "")
    public ResponseEntity<List<JobSkillDtoOut>> getAll(
            @Valid PageableDto pageableDto
    ) {
        return ResponseEntity.ok(jobSkillService.getAll(pageableDto));
    }

    @PostMapping("")
    public ResponseEntity<JobSkillDtoOut> create(@Valid @RequestBody JobSkillDtoIn jobSkillDtoIn) {
        return ResponseEntity.ok(jobSkillService.create(jobSkillDtoIn));
    }

    @GetMapping(path = "/{skillId}")
    public ResponseEntity<JobSkillDtoOut> getById(@PathVariable(name = "skillId") Long skillId) {
        return ResponseEntity.ok(jobSkillService.getById(skillId));
    }
}