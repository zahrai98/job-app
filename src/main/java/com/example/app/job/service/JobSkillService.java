package com.example.app.job.service;

import com.example.app.common.dto.PageableDto;
import com.example.app.configs.exceptions.SystemException;
import com.example.app.job.model.JobSkillsEntity;
import com.example.app.job.model.dto.JobSkillDtoIn;
import com.example.app.job.model.dto.JobSkillDtoOut;
import com.example.app.job.repository.JobSkillsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobSkillService {
    private final JobSkillsRepository jobSkillsRepository;

    public JobSkillService(JobSkillsRepository jobSkillsRepository) {
        this.jobSkillsRepository = jobSkillsRepository;
    }

    public JobSkillDtoOut create(JobSkillDtoIn jonSkillDtoIn) {
        JobSkillsEntity jobSkillsEntity = jonSkillDtoIn.convertToEntity(null);
        jobSkillsRepository.save(jobSkillsEntity);
        return new JobSkillDtoOut(jobSkillsEntity);
    }

    public JobSkillDtoOut getById(Long id) {
        return new JobSkillDtoOut(jobSkillsRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "skill not found", 404)));
    }

    public List<JobSkillDtoOut> getAll(PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getPage() - 1, pageableDto.getSize());
        return jobSkillsRepository.findAll(pageable).stream().map(JobSkillDtoOut::new).collect(Collectors.toList());
    }
}


