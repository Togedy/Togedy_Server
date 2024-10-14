package Togedy.server.Service.Post;

import Togedy.server.Dto.Post.Request.CreatePostRequestDto;
import Togedy.server.Dto.Post.Response.ReadPostsResponseDto;
import Togedy.server.Entity.Board.Post.MarketPost;
import Togedy.server.Entity.Board.PostImage;
import Togedy.server.Repository.PostRepository;
import Togedy.server.Service.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarketPostService {

    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;

    public List<ReadPostsResponseDto> getAllMarketPosts() {
        List<MarketPost> posts = postRepository.findAllMarketPosts();

        return posts.stream()
                .map(ReadPostsResponseDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(CreatePostRequestDto requestDto) {
        List<PostImage> postImages = Optional.ofNullable(requestDto.getPostImages())
                .orElse(Collections.emptyList())
                .stream()
                .map(file -> {
                    try{
                        log.info("Received files: {}", requestDto.getPostImages());
                        String postImgUrl = s3Uploader.upload(file, "postImg");
                        log.info("Post Image URL: {}", postImgUrl);
                        return PostImage.builder().imageUrl(postImgUrl).build();
                    } catch (IOException e) {
                        log.error("File upload failed: {}", file.getOriginalFilename(), e);
                        throw new RuntimeException("Failed to upload file", e);
                    }
                }).collect(Collectors.toList());
        log.info("Uploading files: {}", postImages);

        MarketPost post = requestDto.marketToEntity(postImages);
        postImages.forEach(postImage -> postImage.setPost(post));

        return postRepository.save(post).getId();
    }

}
