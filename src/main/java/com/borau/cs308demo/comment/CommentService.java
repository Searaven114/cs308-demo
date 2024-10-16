package com.borau.cs308demo.comment;

import com.borau.cs308demo.product.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

@Log4j2
@AllArgsConstructor
@Service
public class CommentService {


    private final CommentRepository commentRepo;
    private final ProductRepository productRepo;


    public Comment addComment(String productId, String userId, String content, int rating) {
        Comment comment = new Comment(productId, userId, content, rating);
        return commentRepo.save(comment);
    }


    public List<Comment> getApprovedComments(String productId) {
        return commentRepo.findByProductIdAndApprovedTrue(productId);
    }

    // Admin or product manager can view all comments (including unapproved)
    public List<Comment> getAllCommentsForProduct(String productId) {
        return commentRepo.findByProductId(productId);
    }

    // Approve a comment
    public Comment approveComment(String commentId) {

        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setApproved(true);
        return commentRepo.save(comment);
    }

    // Calculate the average rating for a product
    public double calculateAverageRating(String productId) {
        List<Comment> approvedComments = commentRepo.findByProductIdAndApprovedTrue(productId);
        if (approvedComments.isEmpty()) {
            return 0.0;
        }
        double totalRating = approvedComments.stream().mapToInt(Comment::getRating).sum();
        return totalRating / approvedComments.size();
    }
}

