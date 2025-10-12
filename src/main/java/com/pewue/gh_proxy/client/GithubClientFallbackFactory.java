package com.pewue.gh_proxy.client;

import com.pewue.gh_proxy.dto.GithubRepositoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
                    .createdAt(LocalDateTime.of(1999, 12, 12, 10, 10))
                    .build();
        };
    }
}

