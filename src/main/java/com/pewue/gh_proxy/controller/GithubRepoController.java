package com.pewue.gh_proxy.controller;

import com.pewue.gh_proxy.client.GitHubClient;
import com.pewue.gh_proxy.dto.RepoDetailsDto;
import com.pewue.gh_proxy.mapper.RepoDetailsMapper;
import com.pewue.gh_proxy.service.GithubRepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repositories")
public class GithubRepoController {
    private final GitHubClient gitHubClient;
    private final GithubRepoService githubRepoService;
    private final RepoDetailsMapper mapper;

    @GetMapping("/{owner}/{repo}")
    public RepoDetailsDto getRepositoryDetails(@PathVariable String owner, @PathVariable String repo) {
        return githubRepoService.get(owner, repo);
    }
}
