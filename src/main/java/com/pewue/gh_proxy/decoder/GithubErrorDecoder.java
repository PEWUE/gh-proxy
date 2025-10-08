package com.pewue.gh_proxy.decoder;

import com.pewue.gh_proxy.exception.RepositoryNotFoundException;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class GithubErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 404:
                return new RepositoryNotFoundException("Repository not found");
            case 500:
                return new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Github unexpected error");
            case 503:
                FeignException exception = feign.FeignException.errorStatus(s, response);
                return new RetryableException(
                        response.status(),
                        exception.getMessage(),
                        response.request().httpMethod(),
                        exception,
                        50L,
                        response.request()
                );
            default:
                return defaultErrorDecoder.decode(s, response);
        }
    }
}
