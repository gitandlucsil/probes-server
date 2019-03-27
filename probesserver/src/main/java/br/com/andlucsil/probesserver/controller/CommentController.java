package br.com.andlucsil.probesserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.exception.ResourceNotFoundException;
import br.com.andlucsil.probesserver.model.Comment;
import br.com.andlucsil.probesserver.repository.CommentRepository;
import br.com.andlucsil.probesserver.repository.PostRepository;

@RestController
public class CommentController {

	@Autowired
	    private CommentRepository commentRepository;

	    @Autowired
	    private PostRepository postRepository;

	    @GetMapping("/posts/{postId}/comments")
	    public List<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId) {
	        return commentRepository.findByPostId(postId);
	    }

	    @PostMapping("/posts/{postId}/comments")
	    public Comment createComment(@PathVariable (value = "postId") Long postId,
	                                 @Valid @RequestBody Comment comment) {
	        return postRepository.findById(postId).map(post -> {
	            comment.setPost(post);
	            return commentRepository.save(comment);
	        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	    }

	    @PutMapping("/posts/{postId}/comments/{commentId}")
	    public Comment updateComment(@PathVariable (value = "postId") Long postId,
	                                 @PathVariable (value = "commentId") Long commentId,
	                                 @Valid @RequestBody Comment commentRequest) {
	        if(!postRepository.existsById(postId)) {
	            throw new ResourceNotFoundException("PostId " + postId + " not found");
	        }

	        return commentRepository.findById(commentId).map(comment -> {
	            comment.setText(commentRequest.getText());
	            return commentRepository.save(comment);
	        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
	    }
	    
	    @DeleteMapping("/posts/{postId}/comments/{commentId}")
	    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
	                              @PathVariable (value = "commentId") Long commentId) {
	        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
	            commentRepository.delete(comment);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
	    }

}
