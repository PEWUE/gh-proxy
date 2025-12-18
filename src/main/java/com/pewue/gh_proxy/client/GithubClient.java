package com.pewue.gh_proxy.client;

import com.pewue.gh_proxy.config.GithubClientConfiguration;
import com.pewue.gh_proxy.dto.GithubRepositoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "githubClient", configuration = GithubClientConfiguration.class, fallbackFactory = GithubClientFallbackFactory.class)
public interface GithubClient {

    @GetMapping("/repos/{owner}/{repo}")
    GithubRepositoryResponse getGithubRepo(@PathVariable String owner, @PathVariable String repo);
}
