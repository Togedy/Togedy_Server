package Togedy.server.Entity.Board;

import Togedy.server.Entity.BaseEntity;
import Togedy.server.Entity.BaseStatus;
import Togedy.server.Entity.Board.Post.Post;
import Togedy.server.Entity.User.User;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "Post_Comments")
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "Text")
    private String content;

    @Column(name = "like")
    private int likeCount = 0;


    @Column(name = "target_id")
    private Long targetId;

    @Enumerated(EnumType.STRING)
    private BaseStatus baseStatus = BaseStatus.ACTIVE;
}
