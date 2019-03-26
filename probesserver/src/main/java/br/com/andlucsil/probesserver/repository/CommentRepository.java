package br.com.andlucsil.probesserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.andlucsil.probesserver.model.Comment;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long>{
	List<Comment> findByPostId(Long postId);
    Optional<Comment> findByIdAndPostId(Long id, Long postId);
}
