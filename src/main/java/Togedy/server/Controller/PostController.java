package Togedy.server.Controller;

import Togedy.server.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {

    private final PostService postService;




}
