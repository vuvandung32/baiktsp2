package com.example.baitapkt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessagePass extends RuntimeException {
    private HttpStatus status;
    private String message;
}
