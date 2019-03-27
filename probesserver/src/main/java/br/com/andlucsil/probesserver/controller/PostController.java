package br.com.andlucsil.probesserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.andlucsil.probesserver.exception.ResourceNotFoundException;
import br.com.andlucsil.probesserver.model.Post;
import br.com.andlucsil.probesserver.repository.PostRepository;

@RestController
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/posts")
	public List<Post> getAllPosts(Model model) {
		return postRepository.findAll();
	}
	
	@GetMapping("/posts/{postId}")
	public Post getPost(@PathVariable Long postId) {
		return postRepository.findById(postId).orElse(new Post());
	}

	@PostMapping("/posts")
	public Post createPost(@Valid @RequestBody Post post) {
		return postRepository.save(post);
	}

	@PutMapping("/posts/{postId}")
	public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
		return postRepository.findById(postId).map(post -> {
			post.setTitle(postRequest.getTitle());
			post.setDescription(postRequest.getDescription());
			post.setContent(postRequest.getContent());
			return postRepository.save(post);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	}
	
	  @DeleteMapping("/posts/{postId}")
	    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
	        return postRepository.findById(postId).map(post -> {
	            postRepository.delete(post);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	    }


}
