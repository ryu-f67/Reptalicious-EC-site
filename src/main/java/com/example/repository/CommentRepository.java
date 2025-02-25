package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Comment;
import com.example.entity.Display;
import com.example.entity.Thread;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByThread(Thread thread);
    
    @Query(value="SELECT * FROM comments AS c WHERE c.thread_id = :threadId AND c.display = :#{#display.name()}", nativeQuery = true)
    List<Comment> findByDisplay(@Param("threadId") Long threadId, @Param("display")Display display);
}
