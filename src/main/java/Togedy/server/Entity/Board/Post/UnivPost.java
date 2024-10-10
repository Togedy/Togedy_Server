package Togedy.server.Entity.Board.Post;

import Togedy.server.Entity.AdmissionType;
import Togedy.server.Entity.Board.Post.Post;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("UNIV")
public class UnivPost extends Post {

    private String univName;

    @Enumerated(EnumType.STRING)
    private AdmissionType type;

}
