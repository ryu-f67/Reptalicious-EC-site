package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Comment;
import com.example.entity.Display;
import com.example.entity.Role;
import com.example.entity.Thread;
import com.example.entity.User;
import com.example.repository.CommentRepository;
import com.example.repository.ThreadRepository;

@Service
public class BBSService {

    @Autowired
    private ThreadRepository threadRepository;
    
    @Autowired
    private CommentRepository commentRepository;

    // スレッドリスト ALL
    public List<Thread> getAllThreads() {
        return threadRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    
    // スレッドリスト DISPLAY
    public List<Thread> getDisplayThreads() {
        return threadRepository.findByDisplay(Display.DISPLAY);
    }
    
    // スレッドリスト HIDDEN
    public List<Thread> getHiddenThreads() {
        return threadRepository.findByDisplay(Display.HIDDEN);
    }
    
    // スレッドリスト DELETED
    public List<Thread> getDeletedThreads() {
        return threadRepository.findByDisplay(Display.DELETED);
    }
    
    // スレッド作成
    @Transactional
    public Thread createThread(User user, Thread thread) {
        thread.setUser(user);
        thread.setDisplay(Display.DISPLAY);
        return threadRepository.save(thread);
    }
    
    // IDでスレッドの検索
    public Thread getThreadById(Long id) {
        return threadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("無効なスレッドID: " + id));
    }
    
    // スレッドの削除
    @Transactional
    public Thread removeThread(Thread thread, User user) throws Exception {
        if (!thread.getUser().equals(user) && !(user.getRole() == Role.ROLE_ADMIN)) {
            throw new Exception("スレッドを削除する権限がありません。");
        }
        thread.setDisplay(Display.DELETED);
        return threadRepository.save(thread);
    }
    
    // スレッドの再表示
    @Transactional
    public Thread redisplayThread(Thread thread, User user) throws Exception {
        if (!thread.getUser().equals(user) && !(user.getRole() == Role.ROLE_ADMIN)) {
            throw new Exception("スレッドを再表示される権限がありません。");
        }
        thread.setReport(0);
        thread.setDisplay(Display.DISPLAY);
        return threadRepository.save(thread);
    }
    
    // スレッドの更新
    @Transactional
    public Thread updateThread(Thread thread) {
        thread.setDescription(thread.getDescription());
        return threadRepository.save(thread);
    }
    
    // コメントリスト ALL
    public List<Comment> getAllCommentsByThread(Thread thread) {
        return commentRepository.findByThread(thread);
    }
    
    // コメントリスト DISPLAY
    public List<Comment> getDisplayCommentsByThread(Thread thread) {
        return commentRepository.findByDisplay(thread.getId(), Display.DISPLAY);
    }
    
    // コメントリスト HIDDEN
    public List<Comment> getHiddenCommentsByThread(Thread thread) {
        return commentRepository.findByDisplay(thread.getId(), Display.HIDDEN);
    }
    
    // コメントリスト DELETED
    public List<Comment> getDeletedCommentsByThread(Thread thread) {
        return commentRepository.findByDisplay(thread.getId(), Display.DELETED);
    }
    
    // コメント作成
    @Transactional
    public Comment createComment(User user, Thread thread, Comment comment) {
        comment.setId(null);
        comment.setUser(user);
        comment.setThread(thread);
        comment.setDisplay(Display.DISPLAY);
        return commentRepository.save(comment);
    }
    
    // IDでコメントの検索
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("無効なコメントID: " + id));
    }
    
    // コメントの更新
    @Transactional
    public Comment updateComment(Comment exsistingComment, Comment comment) {
        exsistingComment.setDescription(comment.getDescription());
        return commentRepository.save(exsistingComment);
    }
    
    // コメントの再表示
    @Transactional
    public Comment redisplayComment(Comment comment, User user) throws Exception {
        if (!comment.getUser().equals(user) && !(user.getRole() == Role.ROLE_ADMIN)) {
            throw new Exception("コメントを再表示される権限がありません。");
        }
        comment.setReport(0);
        comment.setDisplay(Display.DISPLAY);
        return commentRepository.save(comment);
    }
    
    // コメントの削除
    @Transactional
    public Comment removeComment(Comment comment, User user) throws Exception {
        if (!comment.getUser().equals(user) && !(user.getRole() == Role.ROLE_ADMIN)) {
            throw new Exception("コメントを削除する権限がありません。");
        }
        comment.setDisplay(Display.DELETED);
        return commentRepository.save(comment);
    }
    
    // スレッドの通報
    @Transactional
    public Thread reportThread(Thread thread) {
        if (thread.getReport() == 4) {
            thread.setReport(thread.getReport()+1);
            thread.setDisplay(Display.HIDDEN);
        } else {
            thread.setReport(thread.getReport()+1);
        }
        return threadRepository.save(thread);
    }
    
    // コメントの通報
    @Transactional
    public Comment reportComment(Comment comment) {
        if (comment.getReport() == 4) {
            comment.setReport(comment.getReport()+1);
            comment.setDisplay(Display.HIDDEN);
        } else {
            comment.setReport(comment.getReport()+1);
        }
        return commentRepository.save(comment);
    }
}
