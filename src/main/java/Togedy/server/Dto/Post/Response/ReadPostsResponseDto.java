package Togedy.server.Dto.Post.Response;

import Togedy.server.Entity.Board.Post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadPostsResponseDto {

    private String title;
    private LocalDateTime createdAt;
    private String content;

    public static ReadPostsResponseDto of (Post post) {
        return ReadPostsResponseDto.builder()
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .content(post.getContent())
                .build();
    }
}
