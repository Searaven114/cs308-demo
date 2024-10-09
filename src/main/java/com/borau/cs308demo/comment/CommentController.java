package com.borau.cs308demo.comment;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {
/*
    private final CommentService commentService;

    // Add a new comment
    @PostMapping("/add")
    public Comment addComment(@RequestParam String productId,
                              @RequestParam String userId,
                              @RequestParam String content,
                              @RequestParam int rating) {
        return commentService.addComment(productId, userId, content, rating);
    }

    // Get all approved comments for a product
    @GetMapping("/approved/{productId}")
    public List<Comment> getApprovedComments(@PathVariable String productId) {
        return commentService.getApprovedComments(productId);
    }

    // Approve a comment (Product Manager or Admin)
    @PatchMapping("/approve/{commentId}")
    public Comment approveComment(@PathVariable String commentId) {
        return commentService.approveComment(commentId);
    }


    // Get all comments for a product (for admin or product manager)
    @GetMapping("/all/{productId}")
    public List<Comment> getAllComments(@PathVariable String productId) {
        return commentService.getAllCommentsForProduct(productId);
    }*/
}
