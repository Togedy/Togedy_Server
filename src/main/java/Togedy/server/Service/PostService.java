package Togedy.server.Service;

import Togedy.server.Dto.Comment.Response.ReadCommentsResponseDto;
import Togedy.server.Dto.Post.Response.ReadPostDetailResponseDto;
import Togedy.server.Entity.Board.Post.Post;
import Togedy.server.Repository.CommentRepository;
import Togedy.server.Repository.PostRepository;
import Togedy.server.Util.Exception.Domain.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static Togedy.server.Util.BaseResponseStatus.POST_NOT_EXIST;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


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
