package Togedy.server.Controller;

import Togedy.server.Dto.Post.Response.ReadPostDetailResponseDto;
import Togedy.server.Service.PostService;
import Togedy.server.Util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {

    private final PostService postService;

    // 게시글 상세 조회
    @GetMapping("/post/{postId}")
    public BaseResponse<ReadPostDetailResponseDto> getPost(
            @PathVariable Long postId,
            @RequestParam Long userId) {
        ReadPostDetailResponseDto responseDto = postService.getPostDetail(postId, userId);
        return new BaseResponse<>(responseDto);

    }
}
