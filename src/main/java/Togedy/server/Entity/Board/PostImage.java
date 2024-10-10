package Togedy.server.Entity.Board;

import Togedy.server.Entity.BaseEntity;
import Togedy.server.Entity.Board.Post.Post;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "Post_Images")
@Getter
public class PostImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    @Column(name = "image_url")
    private String imageUrl;
}
