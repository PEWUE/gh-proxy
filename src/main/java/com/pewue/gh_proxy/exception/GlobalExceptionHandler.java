package com.pewue.gh_proxy.exception;

import com.pewue.gh_proxy.dto.ErrorMessageDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GithubProxyException.class)
    protected ResponseEntity<ErrorMessageDto> handleGithubProxyException(GithubProxyException ex, HttpServletRequest request) {
        ErrorMessageDto error = ErrorMessageDto.builder()
                .timestamp(LocalDateTime.now())
                .status(ex.getStatus().value())
                .error(ex.getStatus().getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ErrorMessageDto> handleUnexpectedException(Exception ex, HttpServletRequest request) {
        ErrorMessageDto error = ErrorMessageDto.builder()
                .timestamp(LocalDateTime.now())
                .status(500)
                .error("Unexpected error")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(500).body(error);
    }
}
