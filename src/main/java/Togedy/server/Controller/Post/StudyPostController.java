package Togedy.server.Controller.Post;

import Togedy.server.Dto.Post.Request.CreatePostRequestDto;
import Togedy.server.Dto.Post.Response.ReadPostsResponseDto;
import Togedy.server.Service.Post.StudyPostService;
import Togedy.server.Util.BaseResponse;
import Togedy.server.Util.Exception.Validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/study")
public class StudyPostController {

    private final StudyPostService studyPostService;

    @GetMapping("")
    public BaseResponse<List<ReadPostsResponseDto>> getFreeBoard() {
        List<ReadPostsResponseDto> board = studyPostService.getAllFreePosts();
        return new BaseResponse<>(board);
    }

    @PostMapping("/post")
    public BaseResponse<Map<String, Long>> createFreePost(
            @ModelAttribute @Validated
            CreatePostRequestDto requestDto,
            BindingResult bindingResult) {

        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);

        Long postId = studyPostService.save(requestDto);

        Map<String, Long> response = new HashMap<>();
        response.put("postId", postId);

        return new BaseResponse<>(response);
    }

}
