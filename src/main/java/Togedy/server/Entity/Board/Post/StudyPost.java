package Togedy.server.Entity.Board.Post;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("STUDY")
@NoArgsConstructor
public class StudyPost extends Post {
}
