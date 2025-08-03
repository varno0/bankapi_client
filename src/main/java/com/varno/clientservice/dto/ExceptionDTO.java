package com.varno.clientservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionDTO {

    private Integer status;

    private String message;

    private LocalDateTime timestamp;

    public ExceptionDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
