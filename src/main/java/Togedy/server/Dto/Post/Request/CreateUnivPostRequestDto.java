package Togedy.server.Dto.Post.Request;

import Togedy.server.Entity.Board.Post.FreePost;
import Togedy.server.Entity.Board.Post.UnivPost;
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
public class CreateUnivPostRequestDto {
    @NotNull(message = "학교 이름은 필수 항목입니다.")
    @Size(min = 1, message = "내용은 최소 1자 이상이어야 합니다.")
    private String univName;

    @NotNull(message = "제목은 필수 항목입니다.")
    @Size(min = 1, message = "내용은 최소 1자 이상이어야 합니다.")
    private String title;

    @NotNull(message = "내용은 필수 항목입니다.")
    @Size(min = 1, message = "내용은 최소 1자 이상이어야 합니다.")
    private String content;

    private List<MultipartFile> postImages;

    public UnivPost toEntity(List<PostImage> postImages) {
        return UnivPost.builder()
                .univName(univName)
                .title(title)
                .content(content)
                .postImages(postImages)
                .build();
    }
}
