package Togedy.server.Service;

import Togedy.server.Entity.Board.Comment;
import Togedy.server.Entity.Board.CommentLike;
import Togedy.server.Entity.Board.Post.Post;
import Togedy.server.Entity.Board.PostLike;
import Togedy.server.Entity.User.User;
import Togedy.server.Repository.*;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.PostException;
import Togedy.server.Util.Exception.Domain.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    //  게시글 좋아요 추가
    @Transactional
    public Long addPostLike(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(BaseResponseStatus.POST_NOT_EXIST));

        // 이미 좋아요 상태인지 확인
        if (postLikeRepository.existsByUserAndPost(user, post)) {
            throw new PostException(BaseResponseStatus.ALREADY_LIKED_THIS_POST);
        }

        PostLike postLike = new PostLike(user, post);

        // 게시글의 좋아요 수 업데이트
        post.setLikeCount(post.getLikeCount() + 1);

        return postLikeRepository.save(postLike).getId();
    }

    // 게시글 좋아요 취소
    @Transactional
    public void removePostLike(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(BaseResponseStatus.POST_NOT_EXIST));

        PostLike postLike = postLikeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new PostException(BaseResponseStatus.LIKE_NOT_EXIST));

        postLikeRepository.delete(postLike);

        // 게시글의 좋아요 수 업데이트
        post.setLikeCount(post.getLikeCount() - 1);
    }

    // 댓글 좋아요
    @Transactional
    public Long addCommentLike(Long userId, Long postId, Long commentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(BaseResponseStatus.POST_NOT_EXIST));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new PostException(BaseResponseStatus.COMMENT_NOT_EXIST));

        // 댓글이 해당 게시글에 속하지 않는 경우 예외 처리
        if (!comment.getPost().equals(post)) {
            throw new PostException(BaseResponseStatus.COMMENT_NOT_BELONG_TO_POST);
        }

        // 이미 좋아요 상태인지 확인
        if (commentLikeRepository.existsByUserAndComment(user, comment)) {
            throw new PostException(BaseResponseStatus.ALREADY_LIKED_THIS_COMMENT);
        }

        CommentLike commentLike = new CommentLike(user, comment);

        // 게시글의 좋아요 수 업데이트
        comment.setLikeCount(comment.getLikeCount() + 1);

        return commentLikeRepository.save(commentLike).getId();
    }

    // 댓글 좋아요 취소
    @Transactional
    public void removeCommentLike(Long userId, Long postId, Long commentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(BaseResponseStatus.POST_NOT_EXIST));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new PostException(BaseResponseStatus.COMMENT_NOT_EXIST));

        // 댓글이 해당 게시글에 속하지 않는 경우 예외 처리
        if (!comment.getPost().equals(post)) {
            throw new PostException(BaseResponseStatus.COMMENT_NOT_BELONG_TO_POST);
        }

        CommentLike commentLike = commentLikeRepository.findByUserAndComment(user, comment)
                .orElseThrow(() -> new PostException(BaseResponseStatus.LIKE_NOT_EXIST));

        commentLikeRepository.delete(commentLike);

        // 댓글의 좋아요 수 업데이트
        comment.setLikeCount(comment.getLikeCount() - 1);
    }
}
