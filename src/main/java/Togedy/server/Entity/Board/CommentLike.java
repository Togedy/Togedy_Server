package Togedy.server.Entity.Board;

import Togedy.server.Entity.BaseEntity;
import Togedy.server.Entity.User.User;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "Comment_Likes")
@Getter
public class CommentLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
