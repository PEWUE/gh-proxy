package com.pewue.gh_proxy.controller;

import com.pewue.gh_proxy.dto.RepositoryDetailsDto;
import com.pewue.gh_proxy.mapper.GithubRepositoryMapper;
import com.pewue.gh_proxy.service.GithubRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/local/repositories")
public class GithubRepositoryLocalController {
    private final GithubRepositoryService githubRepositoryService;
    private final GithubRepositoryMapper githubRepositoryMapper;

    @GetMapping("/{owner}/{repo}")
    public RepositoryDetailsDto getRepositoryDetails(@PathVariable String owner, @PathVariable String repo) {
        return githubRepositoryMapper.toDto(githubRepositoryService.getFromLocal(owner, repo));
    }

    @DeleteMapping("/{owner}/{repo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String owner, @PathVariable String repo) {
        githubRepositoryService.delete(owner, repo);
    }
}
