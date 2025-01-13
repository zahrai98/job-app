package com.example.app.user.service;

import com.example.app.common.dto.PageableDto;
import com.example.app.configs.exceptions.SystemException;
import com.example.app.job.repository.JobSkillsRepository;
import com.example.app.user.model.CandidateEntity;
import com.example.app.user.model.UserEntity;
import com.example.app.user.model.dto.CandidateIn;
import com.example.app.user.model.dto.CandidateOut;
import com.example.app.user.repository.CandidateRepository;
import com.example.app.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    private final JobSkillsRepository jobSkillsRepository;

    public CandidateService(CandidateRepository candidateRepository, UserRepository userRepository, JobSkillsRepository jobSkillsRepository) {
        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
        this.jobSkillsRepository = jobSkillsRepository;
    }

    public CandidateOut create(CandidateIn candidateIn) {
        UserEntity user = userRepository.findById(candidateIn.getUserId()).orElseThrow(
                () -> new SystemException(HttpStatus.NOT_FOUND, "user not found ", 404));
        CandidateEntity candidateEntity = candidateIn.convertToEntity(null);
        candidateEntity.setUser(user);
        candidateEntity.setSkills(jobSkillsRepository.findByIds(candidateIn.getSkills()));
        candidateRepository.save(candidateEntity);
        return new CandidateOut(candidateEntity);
    }

    public CandidateOut getById(Long id) {
        return new CandidateOut(candidateRepository.findByIdWithExperiences(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "candidate not found", 404)));
    }

    public List<CandidateOut> getAll(PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getPage() - 1, pageableDto.getSize());
        Page<CandidateEntity> data = candidateRepository.findAll(pageable);
        return data.stream().map(CandidateOut::new).collect(Collectors.toList());
    }
}
