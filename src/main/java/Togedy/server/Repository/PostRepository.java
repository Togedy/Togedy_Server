package Togedy.server.Repository;

import Togedy.server.Entity.Board.Post.*;
import Togedy.server.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 자유게시판 게시글 조회
    @Query("SELECT p FROM FreePost p WHERE p.baseStatus = 'ACTIVE'")
    List<FreePost> findAllActiveFreePosts();

    // 장터게시판 게시글 조회
    @Query("SELECT p FROM MarketPost p WHERE p.baseStatus = 'ACTIVE'")
    List<MarketPost> findAllActiveMarketPosts();

    // 공부인증게시판 게시글 조회
    @Query("SELECT p FROM StudyPost p WHERE p.baseStatus = 'ACTIVE'")
    List<StudyPost> findAllActiveStudyPosts();

    // 대학별 게시글 조회
    @Query("SELECT p FROM UnivPost p WHERE p.baseStatus = 'ACTIVE' AND p.univName = :univName")
    List<UnivPost> findActiveByUnivName(@Param("univName") String univName);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END " +
            "FROM PostLike l WHERE l.post.id = :postId AND l.user.id = :userId")
    boolean isUserLikedPost(@Param("postId") Long postId, @Param("userId") Long userId);






    List<Post> findByUser(User user); // 사용자에 따른 게시글 조회
}
