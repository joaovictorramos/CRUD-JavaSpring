package com.example.demo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StudentRecordDto(
        @NotEmpty @NotNull String name, 
        @NotEmpty @NotNull String lastname, 
        @NotEmpty @NotNull String registration) {}
