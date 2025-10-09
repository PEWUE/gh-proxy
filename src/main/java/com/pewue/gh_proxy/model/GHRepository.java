package com.pewue.gh_proxy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class GHRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String fullName;
    private String description;
    @Column(unique = true)
    private String cloneUrl;
    private int stars;
    private LocalDateTime createdAt;

    public void update(GHRepository newData) {
        this.fullName = newData.getFullName();
        this.description = newData.getDescription();
        this.cloneUrl = newData.getCloneUrl();
        this.stars = newData.getStars();
        this.createdAt = newData.getCreatedAt();
    }
}
