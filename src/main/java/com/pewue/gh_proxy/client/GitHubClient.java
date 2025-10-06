package com.pewue.gh_proxy.client;

import com.pewue.gh_proxy.dto.GithubRepoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "github", url = "https://api.github.com")
public interface GitHubClient {

    @GetMapping("/repos/{owner}/{repo}")
    GithubRepoResponse getGithubRepo(@PathVariable String owner, @PathVariable String repo);
}
