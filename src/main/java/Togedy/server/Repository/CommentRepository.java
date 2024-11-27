package Togedy.server.Repository;

import Togedy.server.Entity.BaseStatus;
import Togedy.server.Entity.Board.Comment;
import Togedy.server.Entity.Board.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 댓글 조회
    List<Comment> findByPostAndBaseStatus(Post post, BaseStatus baseStatus);

    // 대댓글 조회
    List<Comment> findByTargetId(Long targetId);

    // 유저가 특정 댓글을 좋아요 했는지 확인하는 메서드
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END " +
            "FROM CommentLike l WHERE l.comment.id = :commentId AND l.user.id = :userId")
    boolean isUserLikedComment(@Param("commentId") Long commentId, @Param("userId") Long userId);

}