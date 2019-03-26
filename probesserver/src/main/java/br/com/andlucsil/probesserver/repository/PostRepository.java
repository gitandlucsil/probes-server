package br.com.andlucsil.probesserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.andlucsil.probesserver.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
