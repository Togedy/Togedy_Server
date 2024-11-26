package Togedy.server.Controller;

import Togedy.server.Dto.Post.Request.CreatePostRequestDto;
import Togedy.server.Dto.Post.Response.ReadPostDetailResponseDto;
import Togedy.server.Dto.Post.Response.ReadPostsResponseDto;
import Togedy.server.Security.Auth.AuthMember;
import Togedy.server.Service.PostService;
import Togedy.server.Util.BaseResponse;
import Togedy.server.Util.Exception.Validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {

    private final PostService postService;

    // 게시글 생성
    @PostMapping("/{boardType}/post")
    public BaseResponse<Map<String, Long>> createPost(
            @ModelAttribute @Validated
            CreatePostRequestDto requestDto,
            BindingResult bindingResult,
            @PathVariable String boardType,
            @AuthenticationPrincipal AuthMember authMember) {

        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);

        Long postId = postService.save(authMember.getId(), boardType, requestDto);

        Map<String, Long> response = new HashMap<>();
        response.put("postId", postId);

        return new BaseResponse<>(response);
    }

    // 게시판별 전체 게시글 조회
    @GetMapping("/{boardType}")
    public BaseResponse<List<ReadPostsResponseDto>> getPosts(
            @PathVariable String boardType,
            @RequestParam(required = false) String univName) {
        List<ReadPostsResponseDto> board = postService.getPostsByBoardType(boardType, univName);
        return new BaseResponse<>(board);
    }

    // 게시글 상세 조회
    @GetMapping("/post/{postId}")
    public BaseResponse<ReadPostDetailResponseDto> getPost(
            @PathVariable Long postId,
            @AuthenticationPrincipal AuthMember authMember) {
        ReadPostDetailResponseDto responseDto = postService.getPostDetail(authMember.getId(), postId);
        return new BaseResponse<>(responseDto);

    }

    // 게시글 수정
    @PutMapping("/post/{postId}")
    public BaseResponse<Map<String, Long>> updatePost(
            @PathVariable Long postId,
            @ModelAttribute @Validated CreatePostRequestDto requestDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal AuthMember authMember) {

        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);

        postService.updatePost(authMember.getId(), postId, requestDto);

        Map<String, Long> response = new HashMap<>();
        response.put("postId", postId);

        return new BaseResponse<>(response);
    }




}
