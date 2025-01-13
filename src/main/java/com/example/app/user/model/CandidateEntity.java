package com.example.app.user.model;


import com.example.app.job.model.JobApplicationEntity;
import com.example.app.job.model.JobSkillsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "candidate")
public class CandidateEntity {
    @Id
    @SequenceGenerator(name = "candidate_sequence", sequenceName = "candidate_sequence", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidate_sequence")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "resume")
    private byte[] resume;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private UserEntity user;

    @ManyToMany()
    @JoinTable(
            name = "candidates_skills",
            joinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id")
    )
    private Set<JobSkillsEntity> skills;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "candidate", fetch = FetchType.LAZY)
    private Set<UserExperienceEntity> userExperiences;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<JobApplicationEntity> jobApplications;


}
