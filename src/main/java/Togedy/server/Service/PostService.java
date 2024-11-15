package Togedy.server.Service;

import Togedy.server.Dto.Comment.Response.ReadCommentsResponseDto;
import Togedy.server.Dto.Post.Request.CreatePostRequestDto;
import Togedy.server.Dto.Post.Response.ReadPostDetailResponseDto;
import Togedy.server.Dto.Post.Response.ReadPostsResponseDto;
import Togedy.server.Entity.Board.Post.*;
import Togedy.server.Entity.Board.PostImage;
import Togedy.server.Entity.User.User;
import Togedy.server.Repository.CommentRepository;
import Togedy.server.Repository.PostRepository;
import Togedy.server.Repository.UserRepository;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.CustomException;
import Togedy.server.Util.Exception.Domain.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static Togedy.server.Util.BaseResponseStatus.POST_NOT_EXIST;
import static Togedy.server.Util.BaseResponseStatus.USER_NOT_EXIST;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public Long save(Long userId, String boardType, CreatePostRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        List<PostImage> postImages = Optional.ofNullable(requestDto.getPostImages())
                .orElse(Collections.emptyList())
                .stream()
                .map(file -> {
                    try{
                        log.info("Received files: {}", requestDto.getPostImages());
                        String postImgUrl = s3Uploader.upload(file, "postImg");
                        log.info("Post Image URL: {}", postImgUrl);
                        return PostImage.builder().imageUrl(postImgUrl).build();
                    } catch (IOException e) {
                        log.error("File upload failed: {}", file.getOriginalFilename(), e);
                        throw new RuntimeException("Failed to upload file", e);
                    }
                }).collect(Collectors.toList());
        log.info("Uploading files: {}", postImages);

        Post post;
        switch (boardType.toLowerCase()) {
            case "free":
                post = requestDto.freeToEntity(user, postImages);
                break;
            case "study":
                post = requestDto.studyToEntity(user, postImages);
                break;
            case "market":
                post = requestDto.marketToEntity(user, postImages);
                break;
            case "univ":
                post = requestDto.univToEntity(user, postImages);
                break;
            default:
                throw new IllegalArgumentException("Invalid board type: " + boardType);
        }
        postImages.forEach(image -> image.setPost(post));
        return postRepository.save(post).getId();
    }

    public List<ReadPostsResponseDto> getPostsByBoardType(String boardType, String univName) {
        List<? extends Post> posts;

        // boardType에 따라 매핑할 클래스 지정
        switch (boardType.toLowerCase()) {
            case "free":
                posts = postRepository.findAllFreePosts();
                break;
            case "study":
                posts = postRepository.findAllStudyPosts();
                break;
            case "market":
                posts = postRepository.findAllMarketPosts();
                break;
            case "univ":
                posts = postRepository.findByUnivName(univName);
                break;
            default:
                throw new IllegalArgumentException("Invalid board type: " + boardType);
        }

        return posts.stream()
                .map(ReadPostsResponseDto::of)
                .collect(Collectors.toList());

    }

    public ReadPostDetailResponseDto getPostDetail(Long userId, Long postId) {

        // TODO 1: postId에 해당하는 post find
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_EXIST));

        // TODO 2: 유저가 해당 게시글에 좋아요를 눌렀는지 여부 확인
        boolean isLike = postRepository.isUserLikedPost(post.getId(), userId);

        // TODO 3: 댓글 리스트를 ReadCommentsResponse로 변환
        List<ReadCommentsResponseDto> comments = post.getComments().stream()
                .map(comment -> {
                    int commentCount = commentRepository.countByTargetId(comment.getId());
                    boolean isCommentLiked = commentRepository.isUserLikedComment(comment.getId(), userId);

                    return ReadCommentsResponseDto.of(comment, isCommentLiked, commentCount);
                })
                .collect(Collectors.toList());

        // TODO 7: ReadPostDetailResponse 객체로 변환
        return ReadPostDetailResponseDto.of(post, isLike, comments);
    }
}
