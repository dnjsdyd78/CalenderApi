package service;

import com.sparta.newsfeedproject.domain.Feed;
import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.dto.request.FeedSaveRequestDto;
import com.sparta.newsfeedproject.dto.response.FeedSaveResponseDto;
import com.sparta.newsfeedproject.repository.FeedRepository;
import com.sparta.newsfeedproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedService {
    private FeedRepository feedRepository;
    private UserRepository userRepository;

    @Transactional
    public FeedSaveResponseDto saveFeed(FeedSaveRequestDto feedSaveRequestDto) {
        //UserId로 User객체 조회
        User user = userRepository.findById(feedSaveRequestDto.getUserId()).orElseThrow(()->new NullPointerException("User ID가 존재하지 않습니다."));

        Feed feed = new Feed(user, feedSaveRequestDto.getTitle(), feedSaveRequestDto.getContent(), 0L);
        Feed savedFeed = feedRepository.save(feed);

        return new FeedSaveResponseDto(
                savedFeed.getId(),
                savedFeed.getTitle(),
                savedFeed.getContent(),
                savedFeed.getLikeCount()
        );
    }
}
