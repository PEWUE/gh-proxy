package com.pewue.gh_proxy.mapper;

import com.pewue.gh_proxy.dto.GithubRepositoryResponse;
import com.pewue.gh_proxy.dto.RepositoryDetailsDto;
import com.pewue.gh_proxy.model.GHRepository;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GithubRepositoryMapper {
    RepositoryDetailsDto toDto(GHRepository entity);
    RepositoryDetailsDto toDto(GithubRepositoryResponse response);
    GHRepository toEntity(GithubRepositoryResponse response);
}
