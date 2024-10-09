package com.borau.cs308demo.comment;


import com.borau.cs308demo.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository; // For calculating product rating

    // Add a comment
    /*public Comment addComment(String productId, String userId, String content, int rating) {
        Comment comment = new Comment(productId, userId, content, rating);
        return commentRepository.save(comment);
    }*/

    // Get approved comments for a product
    public List<Comment> getApprovedComments(String productId) {
        return commentRepository.findByProductIdAndApprovedTrue(productId);
    }

    // Admin or product manager can view all comments (including unapproved)
    public List<Comment> getAllCommentsForProduct(String productId) {
        return commentRepository.findByProductId(productId);
    }

    // Approve a comment
    public Comment approveComment(String commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setApproved(true);
        return commentRepository.save(comment);
    }

    // Calculate the average rating for a product
    public double calculateAverageRating(String productId) {
        List<Comment> approvedComments = commentRepository.findByProductIdAndApprovedTrue(productId);
        if (approvedComments.isEmpty()) {
            return 0.0;
        }
        double totalRating = approvedComments.stream().mapToInt(Comment::getRating).sum();
        return totalRating / approvedComments.size();
    }
}

