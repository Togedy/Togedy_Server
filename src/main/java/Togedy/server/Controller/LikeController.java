package Togedy.server.Controller;

import Togedy.server.Security.Auth.AuthMember;
import Togedy.server.Service.LikeService;
import Togedy.server.Util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/post/{postId}")
public class LikeController {

    private final LikeService likeService;

    // 게시글 좋아요
    @PostMapping("/like")
    public BaseResponse<Map<String, Long>> likePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal AuthMember authMember) {

        Long postLikeId = likeService.addPostLike(authMember.getId(), postId);

        Map<String, Long> response = new HashMap<>();
        response.put("postLikeId", postLikeId);

        return new BaseResponse<>(response);
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/like")
    public BaseResponse<Map<String, String>> unlikePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal AuthMember authMember) {

        likeService.removePostLike(authMember.getId(), postId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Unliked the post successfully");

        return new BaseResponse<>(response);
    }

    // 댓글 좋아요
    @PostMapping("/comment/{commentId}/like")
    public BaseResponse<Map<String, Long>> likeComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal AuthMember authMember) {

        Long commentLikeId = likeService.addCommentLike(authMember.getId(), postId, commentId);

        Map<String, Long> response = new HashMap<>();
        response.put("commentLikeId", commentLikeId);

        return new BaseResponse<>(response);
    }


}
