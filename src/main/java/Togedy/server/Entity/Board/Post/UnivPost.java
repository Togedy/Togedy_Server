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

    @Enumerated(EnumType.STRING)
    @Column(name = "admission_type")
    private AdmissionType type;

    @Builder
    public UnivPost(User user, String title, String content, List<PostImage> postImages) {
        super(user, title, content, postImages);
    }

}
