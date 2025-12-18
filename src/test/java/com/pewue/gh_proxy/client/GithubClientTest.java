package com.pewue.gh_proxy.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.pewue.gh_proxy.dto.GithubRepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWireMock(port = 8081)
public class GithubClientTest {
    @Autowired
    WireMockServer wireMockServer;

    @Autowired
    GithubClient githubClient;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnRepo() throws JsonProcessingException {
        GithubRepositoryResponse response = GithubRepositoryResponse.builder()
                .fullName("ownerx/repox")
                .stars(999)
                .cloneUrl("cloneUrl")
                .description("description")
                .build();

        wireMockServer.stubFor(get("/repos/ownerx/repox")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(response))));

        var result = githubClient.getGithubRepo("ownerx", "repox");

        assertAll(
                () -> assertEquals("ownerx/repox", result.getFullName()),
                () -> assertEquals(999, result.getStars()),
                () -> assertEquals("cloneUrl", result.getCloneUrl()),
                () -> assertEquals("description", result.getDescription())
        );
    }
}
