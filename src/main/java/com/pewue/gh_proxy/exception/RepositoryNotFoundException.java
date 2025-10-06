package com.pewue.gh_proxy.exception;

import org.springframework.http.HttpStatus;

public class RepositoryNotFoundException extends GithubProxyException {
    public RepositoryNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
