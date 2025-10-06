package com.pewue.gh_proxy.config;

import com.pewue.gh_proxy.decoder.GithubErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new GithubErrorDecoder();
    }
}
