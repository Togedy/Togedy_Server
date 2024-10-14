package Togedy.server.Entity.Board.Post;

import Togedy.server.Entity.BaseEntity;
import Togedy.server.Entity.BaseStatus;
import Togedy.server.Entity.Board.Comment;
import Togedy.server.Entity.Board.PostImage;
import Togedy.server.Entity.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "Posts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type")
@Getter
@NoArgsConstructor
public abstract class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Column(columnDefinition = "Text")
    private String content;

    @Column(name = "like_count")
    private int likeCount = 0;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> postImages;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "base_status")
    private BaseStatus baseStatus = BaseStatus.ACTIVE;

    public Post (User user, String title, String content, List<PostImage> postImages) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.postImages = postImages;
    }

}
