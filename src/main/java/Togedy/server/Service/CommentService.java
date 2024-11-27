package Togedy.server.Service;

import Togedy.server.Dto.Comment.Request.CreateCommentRequestDto;
import Togedy.server.Entity.Board.Comment;
import Togedy.server.Entity.Board.Post.Post;
import Togedy.server.Entity.User.User;
import Togedy.server.Repository.CommentRepository;
import Togedy.server.Repository.PostRepository;
import Togedy.server.Repository.UserRepository;
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
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    // 댓글 생성
    @Transactional
    public Long createComment(Long userId, Long postId, CreateCommentRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(BaseResponseStatus.POST_NOT_EXIST));

        Comment targetComment = null;

        if (requestDto.getTargetId() != null) {
            targetComment = commentRepository.findById(requestDto.getTargetId())
                    .orElseThrow(() -> new PostException(BaseResponseStatus.COMMENT_NOT_EXIST));
        }

        Comment comment = new Comment(user, post, requestDto.getContent(), targetComment);
        return commentRepository.save(comment).getId();
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long userId, Long postId, Long commentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(BaseResponseStatus.POST_NOT_EXIST));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new PostException(BaseResponseStatus.COMMENT_NOT_EXIST));

        if (!comment.getUser().getId().equals(userId)) {
            throw new UserException(BaseResponseStatus.INVALID_USER);
        }

        comment.updateInactive();
    }
}
