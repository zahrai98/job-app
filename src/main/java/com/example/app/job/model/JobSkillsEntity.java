package com.example.app.job.model;

import com.example.app.user.model.CandidateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "job_skills")
public class JobSkillsEntity {
    @Id
    @SequenceGenerator(name = "job_skills_sequence", sequenceName = "job_skills_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_skills_sequence")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "skills")
    private Set<CandidateEntity> candidates;

    @ManyToMany(mappedBy = "skills")
    private Set<JobPositionEntity> jobPositions;

}
