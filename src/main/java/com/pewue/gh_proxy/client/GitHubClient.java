package com.pewue.gh_proxy.client;

import com.pewue.gh_proxy.dto.GithubRepoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "github", url = "https://api.github.com")
public interface GitHubClient {

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}")
    GithubRepoResponse getGithubRepo(@PathVariable String owner, @PathVariable String repo);
}
