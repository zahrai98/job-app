package com.example.app.job.model;

import com.example.app.job.model.enums.JobApplicationStatus;
import com.example.app.job.model.enums.JobType;
import com.example.app.user.model.CandidateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "job_application")
public class JobApplicationEntity {
    @Id
    @SequenceGenerator(name = "job_application_sequence", sequenceName = "job_application_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_application_sequence")
    private Long id;


    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
//    @ColumnDefault(value = "NOT_SEEN") // TODO
    private JobApplicationStatus status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;


    @ManyToOne
//    @JoinColumn(name = "candidate_id") // TODO
    private CandidateEntity candidate;

    @ManyToOne
//    @JoinColumn(name = "job_position_id") // TODO
    private JobPositionEntity jobPosition;

    @PrePersist
    private void prePersist() {
        if (status == null) {
            status = JobApplicationStatus.NOT_SEEN;
        }
    }

}
