package com.pewue.gh_proxy.service;

import com.pewue.gh_proxy.client.GitHubClient;
import com.pewue.gh_proxy.dto.RepoDetailsDto;
import com.pewue.gh_proxy.mapper.RepoDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubRepoService {
    private final GitHubClient gitHubClient;
    private final RepoDetailsMapper repoDetailsMapper;

    public RepoDetailsDto get(String owner, String repo) {
        return repoDetailsMapper.toDto(gitHubClient.getGithubRepo(owner, repo));
    }
}
