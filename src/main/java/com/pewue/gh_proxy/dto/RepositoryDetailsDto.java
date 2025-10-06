package com.pewue.gh_proxy.dto;

import lombok.Builder;

@Builder
public record RepositoryDetailsDto(
        String fullName,
        String description,
        String cloneUrl,
        int stars,
        String createdAt
) {
}
