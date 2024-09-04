package com.sparta.newsfeedproject.domain;

import com.sparta.newsfeedproject.dto.response.FeedRequestDto;
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

    public Feed update(FeedRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        return this;
    }
}
