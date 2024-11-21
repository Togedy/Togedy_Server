package Togedy.server.Repository;

import Togedy.server.Entity.Calendar.Category;
import Togedy.server.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 카테고리 이름 중복검사
    boolean existsByName(String name);

    //사용자별 카테고리 조회
    List<Category> findByUser(User user);
}
