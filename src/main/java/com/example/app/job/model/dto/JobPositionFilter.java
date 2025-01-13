package com.example.app.job.model.dto;


import com.example.app.job.model.JobPositionEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobPositionFilter {
    private Long id;
    private String title;
    private String description;
    private Integer minExperience;
    private Integer maxExperience;
    private String location;
    private Long skillId;

}
