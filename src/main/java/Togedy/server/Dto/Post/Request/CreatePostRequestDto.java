package Togedy.server.Dto.Post.Request;

import Togedy.server.Entity.Board.Post.*;
import Togedy.server.Entity.Board.PostImage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequestDto {

    @NotNull(message = "제목은 필수 항목입니다.")
    @Size(min = 1, message = "내용은 최소 1자 이상이어야 합니다.")
    private String title;

    @NotNull(message = "내용은 필수 항목입니다.")
    @Size(min = 1, message = "내용은 최소 1자 이상이어야 합니다.")
    private String content;

    private List<MultipartFile> postImages;

    public FreePost freeToEntity(List<PostImage> postImages) {
        return FreePost.builder()
                .title(title)
                .content(content)
                .postImages(postImages)
                .build();
    }

    public MarketPost marketToEntity(List<PostImage> postImages) {
        return MarketPost.builder()
                .title(title)
                .content(content)
                .postImages(postImages)
                .build();
    }

    public StudyPost studyToEntity(List<PostImage> postImages) {
        return StudyPost.builder()
                .title(title)
                .content(content)
                .postImages(postImages)
                .build();
    }

}
