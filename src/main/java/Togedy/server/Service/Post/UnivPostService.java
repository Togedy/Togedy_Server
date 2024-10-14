package Togedy.server.Service.Post;

import Togedy.server.Dto.Post.Request.CreatePostRequestDto;
import Togedy.server.Dto.Post.Response.ReadPostsResponseDto;
import Togedy.server.Entity.Board.Post.UnivPost;
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
public class UnivPostService {

    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;

    public List<ReadPostsResponseDto> getPostsByUnivName(String univName) {
        List<UnivPost> posts = postRepository.findByUnivName(univName);

        return posts.stream()
                .map(ReadPostsResponseDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(CreatePostRequestDto requestDto) {
        log.info("Saving university post for: {}", requestDto.getUnivName());
        log.info("Post title: {}, content: {}", requestDto.getTitle(), requestDto.getContent());

        List<PostImage> postImages = Optional.ofNullable(requestDto.getPostImages())
                .orElse(Collections.emptyList())
                .stream()
                .map(file -> {
                    try {
                        log.info("Uploading file: {}", file.getOriginalFilename());
                        String postImgUrl = s3Uploader.upload(file, "postImg");
                        log.info("File uploaded to S3: {}", postImgUrl);
                        return PostImage.builder().imageUrl(postImgUrl).build();
                    } catch (IOException e) {
                        log.error("File upload failed: {}", file.getOriginalFilename(), e);
                        throw new RuntimeException("Failed to upload file", e);
                    }
                }).collect(Collectors.toList());

        UnivPost post = requestDto.univToEntity(postImages);
        postImages.forEach(postImage -> postImage.setPost(post));

        return postRepository.save(post).getId();
    }


}
