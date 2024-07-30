package br.ufg.inf.backend.stp;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean success;
}
