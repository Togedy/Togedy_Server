package Togedy.server.Dto.Post.Response;

import Togedy.server.Dto.Comment.Response.ReadCommentsResponseDto;
import Togedy.server.Entity.Board.Post.Post;
import Togedy.server.Entity.Board.PostImage;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadPostDetailResponseDto {

    private Long postId;
    private String title;
    private LocalDateTime createdAt;
    private String content;
    private List<String> postImages;

    private int likeCount;
    private int commentCount;
    private List<ReadCommentsResponseDto> comments;
    private boolean isLike;

    public static ReadPostDetailResponseDto of(Post post, boolean isLike, List<ReadCommentsResponseDto> comments, int commentCount){
        List<String> postImageUrls = post.getPostImages().stream()
                .map(PostImage::getImageUrl)
                .toList();

        return ReadPostDetailResponseDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .postImages(postImageUrls)
                .likeCount(post.getLikeCount())
                .commentCount(commentCount)
                .comments(comments)
                .isLike(isLike)
                .build();
    }
}
