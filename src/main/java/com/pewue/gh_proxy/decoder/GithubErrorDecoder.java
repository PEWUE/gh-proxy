package com.pewue.gh_proxy.decoder;

import com.pewue.gh_proxy.exception.RepositoryNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class GithubErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 404:
                return new RepositoryNotFoundException("Repository not found");
            case 500:
                return new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Github unexpected error");
            default:
                return new Exception("Unexpected error");
        }
    }
}
