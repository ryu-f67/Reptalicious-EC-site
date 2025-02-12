package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Comment;
import com.example.entity.Thread;
import com.example.entity.User;
import com.example.security.CustomUserDetails;
import com.example.service.BBSService;
import com.example.service.CategoryService;
import com.example.service.UserService;

@Controller
@RequestMapping("/bbs")
public class BBSController {

    @Autowired
    private BBSService bbsService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;

    // スレッド一覧表示
    @GetMapping
    public String listThreads(@AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {
        model.addAttribute("categories", categoryService.getActiveCategory());
        if (userDetails != null) {
            User user = userService.getUserByEmail(userDetails.getUsername());
            model.addAttribute("user", user);
        }
        List<Thread> threads = bbsService.getDisplayThreads();
        model.addAttribute("threads", threads);
        return "bbs/list";
    }
    
    // スレッド作成画面
    @GetMapping("/thread/new")
    public String showCreateThreadForm(Model model) {
        model.addAttribute("thread", new Thread());
        model.addAttribute("categories", categoryService.getActiveCategory());
        return "bbs/thread/create";
    }
    
    // スレッド作成処理
    @PostMapping("/thread/new")
    public String createThread(@ModelAttribute Thread thread,
                               @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        bbsService.createThread(user, thread);
        return "redirect:/bbs";
    }
    
    // スレッド編集画面
    @GetMapping("/{id}/edit")
    public String showEditThreadForm(@PathVariable Long id, 
                                     @AuthenticationPrincipal CustomUserDetails userDetails,
                                     Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("categories", categoryService.getActiveCategory());
        Thread thread = bbsService.getThreadById(id);
        if (user != thread.getUser()) {
            return "NoAuthority";
        }
        model.addAttribute("thread", thread);
        return "bbs/thread/edit";
    }
    
    // スレッド編集処理
    @PostMapping("/{id}/edit")
    public String updateThread(@PathVariable Long id,
                               @ModelAttribute Thread thread,
                               @AuthenticationPrincipal CustomUserDetails userDetails,
                               Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        Thread exsistingThread = bbsService.getThreadById(id);
        if (user != exsistingThread.getUser()) {
            model.addAttribute("categories", categoryService.getActiveCategory());
            return "NoAuthority";
        }
        bbsService.updateThread(exsistingThread);
        return "redirect:/bbs";
    }
    
    // スレッド詳細
    @GetMapping("/{id}")
    public String showThread(@PathVariable Long id,
                             @AuthenticationPrincipal CustomUserDetails userDetails,
                             Model model) {
        model.addAttribute("categories", categoryService.getActiveCategory());
        if (userDetails != null) {
            User user = userService.getUserByEmail(userDetails.getUsername());
            model.addAttribute("user", user);
        }
        Thread thread = bbsService.getThreadById(id);
        List<Comment> comments = bbsService.getAllCommentsByThread(thread);
        model.addAttribute("thread", thread);
        model.addAttribute("comments", comments);
        return "bbs/detail";
    }
    
    // スレッド削除処理(論理削除)
    @PostMapping("/{id}/remove")
    public String removeThread(@PathVariable Long id,
                               @AuthenticationPrincipal CustomUserDetails userDetails,
                               Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        Thread thread = bbsService.getThreadById(id);
        try {
            bbsService.removeThread(thread, user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", categoryService.getActiveCategory());
            return "operationError";
        }
        return "redirect:/bbs";
    }
    
    // コメント投稿画面
    @GetMapping("/{id}/comment")
    public String showCreateCommentForm(@PathVariable Long id, Model model) {
        Thread thread = bbsService.getThreadById(id);
        model.addAttribute("categories", categoryService.getActiveCategory());
        model.addAttribute("thread", thread);
        model.addAttribute("comment", new Comment());
        return "bbs/comment/create";
    }
    
    // コメント投稿処理
    @PostMapping("/{id}/comment")
    public String createComment(@PathVariable Long id, 
                                @ModelAttribute Comment comment,
                                @ModelAttribute Thread thread,
                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        bbsService.createComment(user, thread, comment);
        return "redirect:/bbs/{id}";
    }
    
    // コメント編集画面
    @GetMapping("/{threadId}/comment/{commentId}/edit")
    public String showEditCommentForm(@PathVariable Long threadId, 
                                      @PathVariable Long commentId,
                                      @AuthenticationPrincipal CustomUserDetails userDetails,
                                      Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        Comment comment = bbsService.getCommentById(commentId);
        model.addAttribute("categories", categoryService.getActiveCategory());
        if (user != comment.getUser()) {
            return "NoAuthority";
        }
        model.addAttribute("comment", comment);
        return "bbs/comment/edit";
    }
    
    // コメント編集処理
    @PostMapping("/{threadId}/comment/{commentId}/edit")
    public String updateComment(@PathVariable Long threadId, 
                                @PathVariable Long commentId,
                                @ModelAttribute Comment comment,
                                @AuthenticationPrincipal CustomUserDetails userDetails,
                                Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        Comment existingComment = bbsService.getCommentById(commentId);
        model.addAttribute("categories", categoryService.getActiveCategory());
        if (user != existingComment.getUser()) {
            return "NoAuthority";
        }
        bbsService.updateComment(existingComment, comment);
        return "redirect:/bbs/{threadId}";
    }
    
    // コメント削除処理(論理削除)
    @PostMapping("/{bbsId}/comment/{commentId}/remove")
    public String removeComment(@PathVariable Long bbsId, 
                                @PathVariable Long commentId,
                                @AuthenticationPrincipal CustomUserDetails userDetails,
                                Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        Comment comment = bbsService.getCommentById(commentId);
        try {
            bbsService.removeComment(comment, user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", categoryService.getActiveCategory());
            return "operationError";
        }
        return "redirect:/bbs/{bbsId}";
    }

    // 通報処理(Thread)
    @PostMapping("/reportThread")
    public String reportThread(@RequestParam Long threadId) {
        Thread thread = bbsService.getThreadById(threadId);
        bbsService.reportThread(thread);
        return "redirect:/bbs";
    }

    // 通報処理(Thread)
    @PostMapping("/reportComment")
    public String reportComment(@RequestParam Long threadId,
                                @RequestParam Long commentId) {
        Comment comment = bbsService.getCommentById(commentId);
        bbsService.reportComment(comment);
        return "redirect:/bbs/" + threadId;
    }
}
