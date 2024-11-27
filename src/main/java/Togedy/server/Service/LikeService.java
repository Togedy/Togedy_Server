package Togedy.server.Service;

import Togedy.server.Entity.Board.Post.Post;
import Togedy.server.Entity.Board.PostLike;
import Togedy.server.Entity.User.User;
import Togedy.server.Repository.PostLikeRepository;
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
public class LikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //  게시글 좋아요 추가
    @Transactional
    public Long addLike(Long userId, Long postId) {
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
    public void removeLike(Long userId, Long postId) {
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
}
