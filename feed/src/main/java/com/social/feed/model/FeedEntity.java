package com.social.feed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(indexes = {
        @Index(name = "owner_index", columnList = "owner")
},
        uniqueConstraints = @UniqueConstraint(columnNames = {"owner", "post"}))
public class FeedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "owner")
    private Long owner;
    @Column(name = "post")
    private Long post;
}
