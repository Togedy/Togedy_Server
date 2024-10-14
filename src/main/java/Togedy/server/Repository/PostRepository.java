package Togedy.server.Repository;

import Togedy.server.Entity.Board.Post.*;
import Togedy.server.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 자유게시판 게시글 조회
    @Query("SELECT p FROM FreePost p")
    List<FreePost> findAllFreePosts();

    // 장터게시판 게시글 조회
    @Query("SELECT p FROM MarketPost p")
    List<MarketPost> findAllMarketPosts();

    // 공부인증게시판 게시글 조회
    @Query("SELECT p FROM StudyPost p")
    List<StudyPost> findAllStudyPosts();

    // 대학별 게시글 조회
    List<UnivPost> findByUnivName(String univName);







    List<Post> findByUser(User user); // 사용자에 따른 게시글 조회
}
