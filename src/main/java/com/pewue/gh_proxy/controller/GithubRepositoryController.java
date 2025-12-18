package com.pewue.gh_proxy.controller;

import com.pewue.gh_proxy.dto.RepositoryDetailsDto;
import com.pewue.gh_proxy.mapper.GithubRepositoryMapper;
import com.pewue.gh_proxy.service.GithubRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repositories")
public class GithubRepositoryController {
    private final GithubRepositoryService githubRepositoryService;
    private final GithubRepositoryMapper githubRepositoryMapper;

    @GetMapping("/{owner}/{repo}")
    public RepositoryDetailsDto getRepositoryDetails(@PathVariable String owner, @PathVariable String repo) {
        return githubRepositoryMapper.toDto(githubRepositoryService.get(owner, repo));
    }

    @PostMapping("/{owner}/{repo}")
    @ResponseStatus(HttpStatus.CREATED)
    public RepositoryDetailsDto add(@PathVariable String owner, @PathVariable String repo) {
        return githubRepositoryMapper.toDto(githubRepositoryService.add(owner, repo));
    }

    @PutMapping("/{owner}/{repo}")
    public RepositoryDetailsDto update(@PathVariable String owner, @PathVariable String repo) {
        return githubRepositoryMapper.toDto(githubRepositoryService.update(owner, repo));
    }
}
