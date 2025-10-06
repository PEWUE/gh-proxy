package com.pewue.gh_proxy.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GithubProxyException extends RuntimeException {
  private final HttpStatus status;

    public GithubProxyException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
