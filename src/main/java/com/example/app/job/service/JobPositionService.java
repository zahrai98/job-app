package com.example.app.job.service;

import com.example.app.common.dto.PageableDto;
import com.example.app.configs.exceptions.SystemException;
import com.example.app.job.model.JobPositionEntity;
import com.example.app.job.model.dto.JobPositionDtoIn;
import com.example.app.job.model.dto.JobPositionDtoOut;
import com.example.app.job.repository.JobPositionRepository;
import com.example.app.job.repository.JobSkillsRepository;
import com.example.app.user.model.UserEntity;
import com.example.app.user.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPositionService {
    private final UserRepository userRepository;
    private final JobSkillsRepository jobSkillsRepository;
    private final JobPositionRepository jobPositionRepository;

    public JobPositionService(UserRepository userRepository, JobSkillsRepository jobSkillsRepository, JobPositionRepository jobPositionRepository) {
        this.userRepository = userRepository;
        this.jobSkillsRepository = jobSkillsRepository;
        this.jobPositionRepository = jobPositionRepository;
    }

    public JobPositionDtoOut create(JobPositionDtoIn jobPositionDtoIn) {
        UserEntity user = userRepository.findById(jobPositionDtoIn.getOwnerId()).orElseThrow(
                () -> new SystemException(HttpStatus.NOT_FOUND, "owner not found ", 404));
        JobPositionEntity jobPositionEntity = jobPositionDtoIn.convertToEntity(null);
        jobPositionEntity.setOwner(user);
        jobPositionEntity.setSkills(jobSkillsRepository.findByIds(jobPositionDtoIn.getSkills()));
        jobPositionRepository.save(jobPositionEntity);
        return new JobPositionDtoOut(jobPositionEntity);
    }

    public JobPositionDtoOut getById(Long id) {
        return new JobPositionDtoOut(jobPositionRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "job not found", 404)));
    }

    public List<JobPositionDtoOut> getAll(PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getPage() - 1, pageableDto.getSize());
        return jobPositionRepository.findAll(pageable).stream().map(JobPositionDtoOut::new).collect(Collectors.toList());
    }
}


