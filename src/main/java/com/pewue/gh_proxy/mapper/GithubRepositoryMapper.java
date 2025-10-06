package com.pewue.gh_proxy.mapper;

import com.pewue.gh_proxy.dto.GithubRepositoryResponse;
import com.pewue.gh_proxy.dto.RepositoryDetailsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GithubRepositoryMapper {
    RepositoryDetailsDto toDto(GithubRepositoryResponse response);
}
