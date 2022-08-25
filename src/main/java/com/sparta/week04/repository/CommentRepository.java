package com.sparta.week04.repository;

import com.sparta.week04.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findAllByPostIdAndId(Long id, Long postid);


    List<Comment> findAllByPostId(Long postid);
}
