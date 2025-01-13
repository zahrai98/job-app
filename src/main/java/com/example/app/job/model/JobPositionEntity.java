package com.example.app.job.model;

import com.example.app.job.model.enums.JobApplicationStatus;
import com.example.app.job.model.enums.JobType;
import com.example.app.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "job_position")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE todo SET is_deleted = true WHERE id=?")
public class JobPositionEntity {
    @Id
    @SequenceGenerator(name = "job_position_sequence", sequenceName = "job_position_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_position_sequence")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "min_experience")
    private Integer minExperience;

    @Column(name = "max_experience")
    private Integer maxExperience;

    @Column(name = "location")
    private String location;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "requirements")
    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "job_position_requirement",
            joinColumns = @JoinColumn(name = "job_position_id"))
    private Set<String> requirements;

    @ManyToMany
    @JoinTable(
            name = "jobs_skills",
            joinColumns = @JoinColumn(name = "job_position_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id")
    )
    private Set<JobSkillsEntity> skills;

    @OneToMany(mappedBy = "jobPosition", cascade = CascadeType.ALL)
    private Set<JobApplicationEntity> jobApplications;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity owner;

    @PrePersist
    private void prePersist() {
        if (isDeleted == null) {
            isDeleted = false;
        }
    }

}
