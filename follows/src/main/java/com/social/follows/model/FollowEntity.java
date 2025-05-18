package com.social.follows.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {
        @Index(name = "followed_index", columnList = "followed_id"),
        @Index(name = "follower_index", columnList = "follower_id")
    },
        uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "followed_id"}))
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "followed_id")
    private Long followedId;
    @Column(name = "follower_id")
    private Long followerId;
}
