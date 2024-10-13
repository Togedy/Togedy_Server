package Togedy.server.Entity.Board.Post;

import Togedy.server.Entity.Board.PostImage;
import Togedy.server.Entity.User.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("FREE")
@NoArgsConstructor
public class FreePost extends Post {
    @Builder
    public FreePost(User user, String title, String content, List<PostImage> postImages) {
        super(user, title, content, postImages);
    }
}
