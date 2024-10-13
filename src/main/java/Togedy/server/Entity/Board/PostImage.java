package Togedy.server.Entity.Board;

import Togedy.server.Entity.BaseEntity;
import Togedy.server.Entity.Board.Post.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "Post_Images")
@Getter
@NoArgsConstructor
public class PostImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    @Column(name = "image_url")
    private String imageUrl;

    @Builder
    public PostImage(String imageUrl) { this.imageUrl = imageUrl; }
}
