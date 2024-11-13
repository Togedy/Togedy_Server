package Togedy.server.Dto.Comment.Response;

import Togedy.server.Entity.Board.Comment;
import Togedy.server.Entity.User.User;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadCommentsResponseDto {
    private Long commentId;

    private String userProfileImg;
    private String userName;

    private String content;

    private int commentCount;
    private int likeCount;
    private boolean isLike;
    private Long targetId;

    public static ReadCommentsResponseDto of(Comment comment, boolean isLike, int commentCount) {
        return ReadCommentsResponseDto.builder()
                .commentId(comment.getId())
                .userProfileImg(comment.getUser().getProfileImageUrl())
                .userName(comment.getUser().getNickname())
                .content(comment.getContent())
                .commentCount(commentCount)
                .likeCount(comment.getLikeCount())
                .isLike(isLike)
                .targetId(comment.getTargetId())
                .build();
    }

}
