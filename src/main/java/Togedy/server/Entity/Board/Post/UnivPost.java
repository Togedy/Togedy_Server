package Togedy.server.Entity.Board.Post;

import Togedy.server.Entity.AdmissionType;
import Togedy.server.Entity.Board.Post.Post;
import Togedy.server.Entity.Board.PostImage;
import Togedy.server.Entity.User.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("UNIV")
@NoArgsConstructor
public class UnivPost extends Post {

    private String univName;

    @Builder
    public UnivPost(User user, String title, String content, String univName, List<PostImage> postImages) {
        super(user, title, content, postImages);
        this.univName = univName;
    }

}
