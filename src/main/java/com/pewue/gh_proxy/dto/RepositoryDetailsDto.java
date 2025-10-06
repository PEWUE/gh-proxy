package com.pewue.gh_proxy.dto;

public record RepositoryDetailsDto(
        String fullName,
        String description,
        String cloneUrl,
        int stars,
        String createdAt
) {
}
