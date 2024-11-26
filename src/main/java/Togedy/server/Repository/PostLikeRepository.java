package Togedy.server.Repository;

import Togedy.server.Entity.Board.Post.Post;
import Togedy.server.Entity.Board.PostLike;
import Togedy.server.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    // 특정 유저가 특정 게시글에 좋아요 추가한 엔티티 조회
    Optional<PostLike> findByUserAndPost(User user, Post post);

    // 특정 유저가 특정 게시글을 좋아요 했는지 확인
    boolean existsByUserAndPost(User user, Post post);
}
