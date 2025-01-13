package com.example.app.common.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageableDto {
    @Min(1)
    @NotNull
    private int size;
    @Min(1)
    @NotNull
    private int page;
}