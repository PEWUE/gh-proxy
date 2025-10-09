package com.pewue.gh_proxy.controller;

import com.pewue.gh_proxy.dto.RepositoryDetailsDto;
import com.pewue.gh_proxy.model.GHRepository;
import com.pewue.gh_proxy.service.GithubRepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GithubRepositoryControllerTest {
    @MockitoBean
    GithubRepositoryService githubRepositoryService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnRepositoryDetailsDtoWhenDataCorrect() throws Exception {
        GHRepository ghRepository = GHRepository.builder()
                .fullName("owner/repo-name")
                .description("Repository description")
                .cloneUrl("https://github.com/owner/repo-name.git")
                .stars(15)
                .createdAt(LocalDateTime.of(2025,8,1,19,50))
                .build();

        when(githubRepositoryService.get(anyString(), anyString())).thenReturn(ghRepository);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/repositories/{owner}/{repo}", "owner", "repo-name")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk(),
                jsonPath("$.fullName").value("owner/repo-name"),
                jsonPath("$.description").value("Repository description"),
                jsonPath("$.cloneUrl").value("https://github.com/owner/repo-name.git"),
                jsonPath("$.stars").value(15),
                jsonPath("$.createdAt").value("2025-08-01T19:50:00")
        );
    }
}
