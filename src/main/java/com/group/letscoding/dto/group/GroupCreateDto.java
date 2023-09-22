package com.group.letscoding.dto.group;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class GroupCreateDto {

    @NotBlank
    private String topic;
    @NotBlank
    private String groupName;
    private String introduction;
    private String skills;
    @Positive
    private int capacity;
    private LocalDate startDate;
    private LocalDate endDate;
}