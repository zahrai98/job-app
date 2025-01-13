package com.example.app.user.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "user_experience")
public class UserExperienceEntity {
    @Id
    @SequenceGenerator(name = "user_experience_sequence", sequenceName = "user_experience_sequence", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_experience_sequence")
    private Long id;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private CandidateEntity candidate;

    public boolean isCurrentJob() {
        return endDate == null;
    }

}
