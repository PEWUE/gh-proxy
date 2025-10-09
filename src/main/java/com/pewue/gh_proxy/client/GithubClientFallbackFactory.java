package com.pewue.gh_proxy.client;

import com.pewue.gh_proxy.dto.GithubRepositoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GithubClientFallbackFactory implements FallbackFactory<GithubClient> {
    @Override
    public GithubClient create(Throwable cause) {
        return (owner, repo) -> {
            log.warn("FALLBACK");
            return GithubRepositoryResponse.builder()
                    .fullName("fallbackName")
                    .description("This is fallback")
                    .cloneUrl("fallbackUrl")
                    .stars(0)
                    .createdAt("1995-11-14T21:37:00")
                    .build();
        };
    }
}

