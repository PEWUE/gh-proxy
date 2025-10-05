package com.pewue.gh_proxy.mapper;

import com.pewue.gh_proxy.dto.GithubRepoResponse;
import com.pewue.gh_proxy.dto.RepoDetailsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RepoDetailsMapper {
    RepoDetailsDto toDto(GithubRepoResponse response);
}
