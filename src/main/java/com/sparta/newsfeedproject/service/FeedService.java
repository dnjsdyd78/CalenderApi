package com.sparta.newsfeedproject.service;

import com.sparta.newsfeedproject.domain.Feed;
import com.sparta.newsfeedproject.domain.Follow;
import com.sparta.newsfeedproject.domain.User;
import com.sparta.newsfeedproject.dto.request.FeedRequestDto;
import com.sparta.newsfeedproject.dto.request.FeedSaveRequestDto;
import com.sparta.newsfeedproject.dto.request.UserTokenDto;
import com.sparta.newsfeedproject.dto.request.UserDto;
import com.sparta.newsfeedproject.dto.request.UserTokenDto;
import com.sparta.newsfeedproject.dto.response.*;
import com.sparta.newsfeedproject.repository.FeedRepository;
import com.sparta.newsfeedproject.repository.FollowRepository;
import com.sparta.newsfeedproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class FeedService {


    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;


    @Transactional
    public FeedResponseDto updateFeed(Long id, UserTokenDto loginUser, FeedRequestDto requestDto) throws AccessDeniedException {
        // 게시글 작성 유저계정찾기
        Feed feed = feedRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 로그인 유저아이디, 게시글 작성아이디값 추출
        Long feedUserId = feed.getUser().getId();
        Long loginUserId = loginUser.getUserId();

        // 아이디 일치시 수정실행, 미일치시 예외처리
        try {
            if (feedUserId == loginUserId) {
                feed.update(requestDto);
                return new FeedResponseDto(feed);
            } else {
                throw new AccessDeniedException("자신의 게시글만 수정할 수 있습니다.");
            }
        } catch (AccessDeniedException e) {
            log.error(e.getMessage());
            throw new AccessDeniedException("자신의 게시글만 삭제할 수 있습니다.");
        }

    }

    public void deleteFeed(Long id, UserTokenDto loginUser) throws AccessDeniedException {
        // 게시글 작성 유저계정찾기
        Feed feed = feedRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 로그인 유저아이디, 게시글 작성아이디값 추출
        Long feedUserId = feed.getUser().getId();
        Long loginUserId = loginUser.getUserId();

        // 아이디 일치시 수정실행, 미일치시 예외처리
        try {
            if (feedUserId == loginUserId) {
                feedRepository.delete(feed);
            } else {
                throw new AccessDeniedException("자신의 게시글만 삭제할 수 있습니다.");
            }
        } catch (AccessDeniedException e) {
            log.error(e.getMessage());
            throw new AccessDeniedException("자신의 게시글만 삭제할 수 있습니다.");
        }
    }

    //게시글(피드) 작성
    @org.springframework.transaction.annotation.Transactional
    public FeedSaveResponseDto saveFeed(UserTokenDto tokenUser,FeedSaveRequestDto feedSaveRequestDto) {
        //UserId로 User객체 조회
        User user = userRepository.findById(tokenUser.getUserId()).orElseThrow(() -> new NullPointerException("User ID가 존재하지 않습니다."));

        Feed feed = new Feed(user, feedSaveRequestDto.getTitle(), feedSaveRequestDto.getContent(), 0L);
        Feed savedFeed = feedRepository.save(feed);

        return new FeedSaveResponseDto(
                savedFeed.getId(),
                savedFeed.getTitle(),
                savedFeed.getContent(),
                savedFeed.getLikeCount()
        );
    }

    //특정 유저의 public interface FeedRepository extends JpaRepository<Feed, Long> {
    //    Page<Feed> findByUserId(User userId, PageRequest pageRequest);
    //}피드 목록 조회
    public Page<FeedSimpleResponseDto> getFeeds(UserTokenDto tokenUser, int page) {
        //제공된 사용자 ID(id)를 기반으로 해당 사용자의 피드만을 조회하므로, 기본적으로 다른 사용자의 피드를 조회할 수 없음

        // ->로그인한 사용자와 요청된 id가 일치하는지 확인하는 로직을 추가하는게 좋을까..? 굳이 안해도 되나?
        User user = userRepository.findById(tokenUser.getUserId()).orElseThrow(() -> new NullPointerException("User ID가 존재하지 않습니다."));

        //10개씩 페이지네이션 : 각 페이지 당 뉴스피드 데이터가 10개씩 나오게 + 기본정렬이 생성일자 기준 내림차순
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Feed> feedPage = feedRepository.findByUserId(user.getId(), pageRequest);

        //각 피드 엔티티를 DetailResponseDto로 변환해서 페이지 결과 반환
        return feedPage.map(feed -> new FeedSimpleResponseDto(
                feed.getId(),
                feed.getTitle(),
                feed.getContent(),
                feed.getLikeCount()

        ));
    }

    //개별 피드 조회
    public FeedDetailResponseDto getFeedDetail(Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new NullPointerException("피드가 없습니다."));

        return new FeedDetailResponseDto(
                feed.getId(),
                feed.getTitle(),
                feed.getContent(),
                feed.getUser().getUserName(),
                feed.getLikeCount(),
                feed.getCreatedAt().toString(),
                feed.getUpdatedAt().toString()
        );
    }

    //팔로우한 사람들의 피드 목록 보기
    public Page<FeedSimpleResponseDto> getFollowFeeds(UserTokenDto tokenUser, int page){
        User user = userRepository.findById(tokenUser.getUserId()).orElseThrow(()->new NullPointerException("user Id가 유효하지 않습니다."));

        //사용자가 팔로우한 사용자목록(팔로우목록) 조회
        List<Follow> followList = followRepository.findAllByFollowingId(user);
        List<User> followedUsers = followList.stream()
                .map(Follow::getStandardId) //<-follow 엔티티에서 팔로우 된 사용자 객체 가져오ㄹ ㅑ고
                .collect(Collectors.toList());

        ////10개씩 페이지네이션 : 각 페이지 당 뉴스피드 데이터가 10개씩 나오게 + 기본정렬이 생성일자 기준 내림차순
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        //팔로우된 사용자들의 피드를 페이징 하면서 조회
        Page<Feed> feedPage = feedRepository.findByUserIdIn(followedUsers, pageRequest);

        return feedPage.map(feed -> new FeedSimpleResponseDto(
                feed.getId(),
                feed.getTitle(),
                feed.getContent(),
                feed.getLikeCount()
        ));

    }

}
