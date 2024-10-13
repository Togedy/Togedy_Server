package Togedy.server.Service;

import Togedy.server.Dto.Post.Request.CreatePostRequestDto;
import Togedy.server.Dto.Post.Response.ReadPostsResponseDto;
import Togedy.server.Entity.Board.Post.FreePost;
import Togedy.server.Entity.Board.Post.Post;
import Togedy.server.Entity.Board.Post.UnivPost;
import Togedy.server.Entity.Board.PostImage;
import Togedy.server.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
//    private final S3Uploader s3Uploader;

    // 자유게시판
//    public List<ReadPostsResponseDto> getAllFreePosts() {
//        List<FreePost> posts = postRepository.findAllFreePosts();
//
//        return posts.stream()
//                .map(ReadPostsResponseDto::of)
//                .collect(Collectors.toList());
//    }
    // 대학별 게시판
    public List<ReadPostsResponseDto> getAllUnivPosts() {
        List<UnivPost> posts = postRepository.findAllUnivPosts();

        return posts.stream()
                .map(ReadPostsResponseDto::of)
                .collect(Collectors.toList());
    }

//    @Transactional
//    public Long save(CreatePostRequestDto createPostRequestDto) {
////        List<PostImage> postImages = Optional.ofNullable(createPostRequestDto.getPostImages())
////                .orElse(Collections.emptyList())
////                .stream()
////                .map(file -> {
////                    try{
////                        log.info("Received files: {}", createPostRequestDto.getPostImages());
////                        String postImgUrl = s3Uploader.upload(file, "postImg");
////                        log.info("Post Image URL: {}", postImgUrl);
////                        return PostImage.builder().imageUrl(postImgUrl).build();
////                    } catch (IOException e) {
////                        throw new RuntimeException("Failed to upload file", e);
////                    }
////                }).collect(Collectors.toList());
////        log.info("Uploading files: {}", postImages);
//        List<PostImage> postImages = Optional.ofNullable(createPostRequestDto.getPostImages())
//                .orElse(Collections.emptyList());
//
//        Post post = createPostRequestDto.toEntity(postImages);
//        postImages.forEach(postImage -> postImage.setPost(post));
//
//        return postRepository.save(post).getId();
//    }



}
