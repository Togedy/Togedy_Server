package Togedy.server.Dto.Post.Response;

import Togedy.server.Entity.Board.Post.Post;
import Togedy.server.Entity.Board.PostImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadPostsResponseDto {

    private String title;
    private LocalDateTime createdAt;
    private String content;
    private List<String> postImages;

    public static ReadPostsResponseDto of (Post post) {
        List<String> postImages = post.getPostImages().stream()
                .map(PostImage::getImageUrl)
                .toList();

        return ReadPostsResponseDto.builder()
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .content(post.getContent())
                .postImages(postImages)
                .build();
    }
}
