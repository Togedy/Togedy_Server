package Togedy.server.Controller.Post;

import Togedy.server.Dto.Post.Request.CreatePostRequestDto;
import Togedy.server.Dto.Post.Response.ReadPostsResponseDto;
import Togedy.server.Service.Post.FreePostService;
import Togedy.server.Util.BaseResponse;
import Togedy.server.Util.Exception.Validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/free")
public class FreePostController {

    private final FreePostService freePostService;


    /**
     * 게시글 조회
     */

    @GetMapping("")
    public BaseResponse<List<ReadPostsResponseDto>> getFreeBoard() {
        List<ReadPostsResponseDto> board = freePostService.getAllFreePosts();
        return new BaseResponse<>(board);
    }


    /**
     * 게시글 작성
     */
    @PostMapping("/post")
    public BaseResponse<Long> createPost(
            @ModelAttribute @Validated CreatePostRequestDto createPostRequestDto,
            BindingResult bindingResult) {

        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);

        Long postId = freePostService.save(createPostRequestDto);

        return new BaseResponse<>(postId);
    }
}
