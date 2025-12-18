package com.pewue.gh_proxy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("repositories")
public class GHRepository {
    @Id
    private String id;
    @Indexed(unique = true)
    private String fullName;
    private String description;
    @Indexed(unique = true)
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
