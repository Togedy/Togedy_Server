package Togedy.server.Dto.Post.Request;

import Togedy.server.Entity.Board.Post.*;
import Togedy.server.Entity.Board.PostImage;
import Togedy.server.Entity.User.User;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "제목은 필수 항목입니다.")
    @Size(min = 1, message = "내용은 최소 1자 이상이어야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수 항목입니다.")
    @Size(min = 1, message = "내용은 최소 1자 이상이어야 합니다.")
    private String content;

    private List<MultipartFile> postImages;

    private MarketStatus marketStatus; // 장터 게시판에 필요한 필드

    private String univName; // 대학별 게시판에 필요한 필드

    public FreePost freeToEntity(User user, List<PostImage> postImages) {
        return FreePost.builder()
                .user(user)
                .title(title)
                .content(content)
                .postImages(postImages)
                .build();
    }

    public MarketPost marketToEntity(User user, List<PostImage> postImages) {
        return MarketPost.builder()
                .user(user)
                .title(title)
                .content(content)
                .status(marketStatus != null ? marketStatus : MarketStatus.ONGOING)  // marketStatus가 null일 때 기본값 설정)
                .postImages(postImages)
                .build();
    }

    public StudyPost studyToEntity(User user, List<PostImage> postImages) {
        return StudyPost.builder()
                .user(user)
                .title(title)
                .content(content)
                .postImages(postImages)
                .build();
    }

    public UnivPost univToEntity(User user, List<PostImage> postImages) {
        return UnivPost.builder()
                .user(user)
                .univName(univName)
                .title(title)
                .content(content)
                .postImages(postImages)
                .build();
    }



}
