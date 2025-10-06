package com.pewue.gh_proxy.controller;

import com.pewue.gh_proxy.dto.RepositoryDetailsDto;
import com.pewue.gh_proxy.service.GithubRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repositories")
public class GithubRepositoryController {
    private final GithubRepositoryService githubRepoService;

    @GetMapping("/{owner}/{repo}")
    public RepositoryDetailsDto getRepositoryDetails(@PathVariable String owner, @PathVariable String repo) {
        return githubRepoService.get(owner, repo);
    }
}
