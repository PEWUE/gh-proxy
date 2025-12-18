package com.pewue.gh_proxy.service;

import com.pewue.gh_proxy.client.GithubClient;
import com.pewue.gh_proxy.dto.GithubRepositoryResponse;
import com.pewue.gh_proxy.dto.RepositoryDetailsDto;
import com.pewue.gh_proxy.exception.RepositoryNotFoundException;
import com.pewue.gh_proxy.mapper.GithubRepositoryMapper;
import com.pewue.gh_proxy.model.GHRepository;
import com.pewue.gh_proxy.repository.GithubRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GithubRepositoryServiceTest {
    GithubClient githubClient;
    GithubRepositoryMapper githubRepositoryMapper;
    GithubRepositoryService githubRepositoryService;
    GithubRepository githubRepository;


    @BeforeEach
    void setup() {
        this.githubClient = mock(GithubClient.class);
        this.githubRepositoryMapper = Mappers.getMapper(GithubRepositoryMapper.class);
        this.githubRepository = mock(GithubRepository.class);
        this.githubRepositoryService = new GithubRepositoryService(githubClient, githubRepositoryMapper, githubRepository);
    }

    @Test
    void shouldReturnRepositoryDetailsWhenDataCorrect() {
        GithubRepositoryResponse response = GithubRepositoryResponse.builder()
                .fullName("testOwner/test-repo-name")
                .description("Repository description")
                .cloneUrl("https://github.com/testOwner/test-repo-name.git")
                .stars(155)
                .createdAt(LocalDateTime.of(2025,8,1,19,50))
                .build();

        when(githubClient.getGithubRepo(anyString(), anyString())).thenReturn(response);

        GHRepository result = githubRepositoryService.get("owner", "repoName");

        assertAll(
                () -> assertEquals("testOwner/test-repo-name", result.getFullName()),
                () -> assertEquals("Repository description", result.getDescription()),
                () -> assertEquals("https://github.com/testOwner/test-repo-name.git", result.getCloneUrl()),
                () -> assertEquals(155, result.getStars()),
                () -> assertEquals(LocalDateTime.of(2025,8,1,19,50), result.getCreatedAt())
        );
    }

    @Test
    void shouldThrowRepositoryNotFoundException() {
        when(githubClient.getGithubRepo(anyString(), anyString()))
                .thenThrow(new RepositoryNotFoundException("Repository not found"));

        RepositoryNotFoundException exception = assertThrows(RepositoryNotFoundException.class, () -> {
            githubRepositoryService.get("owner", "repoName");
        });

        assertAll(
                () -> assertEquals("Repository not found", exception.getMessage()),
                () -> assertEquals(HttpStatus.NOT_FOUND, exception.getStatus())
        );
    }

    @Test
    void shouldThrowHttpServerErrorException() {
        when(githubClient.getGithubRepo(anyString(), anyString()))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Github unexpected error"));

        HttpServerErrorException exception = assertThrows(HttpServerErrorException.class, () -> {
            githubRepositoryService.get("owner", "repoName");
        });

        assertAll(
                () -> assertEquals("Github unexpected error", exception.getStatusText()),
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode())
        );
    }
}
