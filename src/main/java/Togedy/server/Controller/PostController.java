package Togedy.server.Controller;

import Togedy.server.Dto.Post.Request.CreatePostRequestDto;
import Togedy.server.Dto.Post.Response.ReadPostsResponseDto;
import Togedy.server.Service.PostService;
import Togedy.server.Util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {
    private final PostService postService;





//    @PostMapping("/board/post")
//    public BaseResponse<Long> createPost(
//            @ModelAttribute @Validated CreatePostRequestDto createPostRequestDto,
//            BindingResult bindingResult) {
//        Long postId = postService.save(createPostRequestDto);
//
//        return new BaseResponse<>(postId);
//    }



}
