package com.sparta.newsfeedproject.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "follow")
@EntityListeners(AuditingEntityListener.class)

public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 기준이 되는 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "standard_id")
    private User standardId;

    // 기준이 팔로우 하는 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User followingId;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
