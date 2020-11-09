package com.crud.tasks.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class Mail {
    @NotNull
    private final String mailTo;
    @NotNull
    private final String subject;
    @NotNull
    private final String message;
    private final String toCc;
}