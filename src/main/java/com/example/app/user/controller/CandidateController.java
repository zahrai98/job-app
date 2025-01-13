package com.example.app.user.controller;

import com.example.app.common.dto.PageableDto;
import com.example.app.user.model.dto.*;
import com.example.app.user.service.CandidateService;
import com.example.app.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/candidate")
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping(path = "")
    public ResponseEntity<List<CandidateOut>> getAll(
            @Valid PageableDto pageableDto
    ) {
        return ResponseEntity.ok(candidateService.getAll(pageableDto));
    }

    @PostMapping("")
    public ResponseEntity<CandidateOut> create(@Valid @RequestBody CandidateIn candidateIn) {
        return ResponseEntity.ok(candidateService.create(candidateIn));
    }

    @GetMapping(path = "/{candidateId}")
    public ResponseEntity<CandidateOut> getById(@PathVariable(name = "candidateId") Long candidateId) {
        return ResponseEntity.ok(candidateService.getById(candidateId));
    }
}