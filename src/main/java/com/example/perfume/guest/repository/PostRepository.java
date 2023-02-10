package com.example.perfume.guest.repository;


import com.example.perfume.guest.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    Optional<Post> findByVisitor(String visitor);

    Optional<Post> findByContentContaining(String content);
}
