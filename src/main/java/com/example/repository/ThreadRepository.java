package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Display;
import com.example.entity.Thread;

public interface ThreadRepository extends JpaRepository<Thread, Long> {

    List<Thread> findByDisplay(Display display);
}
