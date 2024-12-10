package Togedy.server.Dto.Comment.Response;

import Togedy.server.Entity.BaseStatus;
import Togedy.server.Entity.Board.Comment;
import Togedy.server.Entity.User.User;
import lombok.*;

import java.util.List;

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

    private int likeCount;
    private boolean isCommentLike;
    private BaseStatus commentStatus;
    private List<ReadCommentsResponseDto> replies; // 대댓글 리스트

    public static ReadCommentsResponseDto of(Comment comment, boolean isLike, List<ReadCommentsResponseDto> replies) {
        return ReadCommentsResponseDto.builder()
                .commentId(comment.getId())
                .userProfileImg(comment.getUser().getProfileImageUrl())
                .userName(comment.getUser().getNickname())
                .content(comment.getContent())
                .likeCount(comment.getLikeCount())
                .isCommentLike(isLike)
                .commentStatus(comment.getBaseStatus())
                .replies(replies)
                .build();
    }

}
