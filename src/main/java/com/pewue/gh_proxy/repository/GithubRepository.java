package com.pewue.gh_proxy.repository;

import com.pewue.gh_proxy.model.GHRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GithubRepository extends JpaRepository<GHRepository, Long> {
    Optional<GHRepository> findByFullName(String fullName);
}
