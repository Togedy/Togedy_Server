package Togedy.server.Dto.Comment.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequestDto {

    private String content;
    private Long targetId;
}
