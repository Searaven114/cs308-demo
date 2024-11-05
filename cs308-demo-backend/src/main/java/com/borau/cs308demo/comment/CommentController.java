package com.borau.cs308demo.comment;

import com.borau.cs308demo.comment.dto.CommentDTO;
import com.borau.cs308demo.user.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

//        private String productId;
//        private String userId;
//        private String content;
//        private int rating;

    @PostMapping("/")
    public Comment addComment(@RequestBody @Valid CommentDTO dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        commentService.addComment(user.getId(), dto);

        return null;
    }


    @GetMapping("/{productId}")
    public List<Comment> getApprovedComments(@PathVariable String productId) {
        return commentService.getApprovedComments(productId);
    }


}
