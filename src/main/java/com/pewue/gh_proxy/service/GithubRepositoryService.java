package com.pewue.gh_proxy.service;

import com.pewue.gh_proxy.client.GithubClient;
import com.pewue.gh_proxy.dto.GithubRepositoryResponse;
import com.pewue.gh_proxy.exception.RepositoryNotFoundException;
import com.pewue.gh_proxy.mapper.GithubRepositoryMapper;
import com.pewue.gh_proxy.model.GHRepository;
import com.pewue.gh_proxy.repository.GithubRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubRepositoryService {
    private final GithubClient githubClient;
    private final GithubRepositoryMapper githubRepositoryMapper;
    private final GithubRepository githubRepository;

    public GHRepository get(String owner, String repo) {
        return githubRepositoryMapper.toEntity(githubClient.getGithubRepo(owner, repo));
    }

    @Transactional
    public GHRepository add(String owner, String repo) {
        GHRepository repository = githubRepositoryMapper.toEntity(githubClient.getGithubRepo(owner, repo));
        return githubRepository.save(repository);
    }

    public GHRepository getFromLocal(String owner, String repo) {
        return githubRepository.findByFullName(getFullName(owner, repo))
                .orElseThrow(() -> new RepositoryNotFoundException("Repository " + repo + " not found"));
    }
    @Transactional
    public GHRepository update(String owner, String repo) {
        GithubRepositoryResponse repository = githubClient.getGithubRepo(owner, repo);
        GHRepository existingRepo = getFromLocal(owner, repo);
        existingRepo.update(githubRepositoryMapper.toEntity(repository));
        return githubRepository.save(existingRepo);
    }

    @Transactional
    public void delete(String owner, String repo) {
        GHRepository existingRepo = getFromLocal(owner, repo);
        githubRepository.delete(existingRepo);
    }

    private String getFullName(String owner, String repo) {
        return owner + "/" + repo;
    }
}
