package Togedy.server.Repository;

import Togedy.server.Entity.Board.Comment;
import Togedy.server.Entity.Board.CommentLike;
import Togedy.server.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    // 특정 유저가 특정 게시글에 좋아요 추가한 엔티티 조회
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);

    // 특정 유저가 특정 게시글을 좋아요 했는지 확인
    boolean existsByUserAndComment(User user, Comment comment);
}
