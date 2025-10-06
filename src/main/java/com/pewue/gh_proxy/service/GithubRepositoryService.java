package com.pewue.gh_proxy.service;

import com.pewue.gh_proxy.client.GithubClient;
import com.pewue.gh_proxy.dto.RepositoryDetailsDto;
import com.pewue.gh_proxy.mapper.GithubRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubRepositoryService {
    private final GithubClient gitHubClient;
    private final GithubRepositoryMapper repoDetailsMapper;

    public RepositoryDetailsDto get(String owner, String repo) {
        return repoDetailsMapper.toDto(gitHubClient.getGithubRepo(owner, repo));
    }
}
