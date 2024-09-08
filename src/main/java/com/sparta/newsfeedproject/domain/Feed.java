package com.sparta.newsfeedproject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feed")
public class Feed extends BaseTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 50)
    private String title;

    private String content;

    @Column(nullable = false, name = "like_count")
    private Long likeCount;


    public Feed(User user,String title, String content, Long likeCount) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
    }

    public Feed update(Feed updatedFeed) {
        this.title = updatedFeed.title;
        this.content = updatedFeed.content;
        return this;
    }

}
