package com.example.controller.admin;

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

import com.example.entity.Comment;
import com.example.entity.Thread;
import com.example.entity.User;
import com.example.security.CustomUserDetails;
import com.example.service.BBSService;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin/bbs")
public class AdminBBSController {
    
    @Autowired
    private BBSService bbsService;
    
    @Autowired
    private UserService userService;
    
    // すべてのスレッドを表示
    @GetMapping("/threads/all")
    public String listAllThreads(Model model) {
        model.addAttribute("threads", bbsService.getAllThreads());
        return "admin/bbs/thread/list";
    }
    
    // 表示状態のスレッドを表示
    @GetMapping("/threads/display")
    public String listDisplayThreads(Model model) {
        model.addAttribute("threads", bbsService.getDisplayThreads());
        return "admin/bbs/thread/list";
    }
    
    // 非表示状態のスレッドを表示
    @GetMapping("/threads/hidden")
    public String listHiddenThreads(Model model) {
        model.addAttribute("threads", bbsService.getHiddenThreads());
        return "admin/bbs/thread/list";
    }
    
    // 削除済状態のスレッドを表示
    @GetMapping("/threads/deleted")
    public String listDeletedThreads(Model model) {
        model.addAttribute("threads", bbsService.getDeletedThreads());
        return "admin/bbs/thread/list";
    }
    
    // スレッドの詳細を表示
    @GetMapping("/threads/{threadsId}")
    public String showThread(@PathVariable Long threadsId, Model model) {
        model.addAttribute("thread", bbsService.getThreadById(threadsId));
        return "admin/bbs/thread/detail";
    }
    
    // すべてのコメントを表示
    @GetMapping("/threads/{threadsId}/comments/all")
    public String listAllComments(@PathVariable Long threadsId, Model model) {
        Thread thread = bbsService.getThreadById(threadsId);
        List<Comment> comments = bbsService.getAllCommentsByThread(thread);
        model.addAttribute("thread", thread);
        model.addAttribute("comments", comments);
        return "admin/bbs/comment/list";
    }
    
    // 表示状態のコメントを表示
    @GetMapping("/threads/{threadId}/comments/display")
    public String listDisplayComments(@PathVariable Long threadId, Model model) {
        Thread thread = bbsService.getThreadById(threadId);
        List<Comment> comments = bbsService.getDisplayCommentsByThread(thread);
        model.addAttribute("thread", thread);
        model.addAttribute("comments", comments);
        return "admin/bbs/comment/list";
    }
    
    // 非表示状態のコメントを表示
    @GetMapping("/threads/{threadId}/comments/hidden")
    public String listHiddenComments(@PathVariable Long threadId, Model model) {
        Thread thread = bbsService.getThreadById(threadId);
        List<Comment> comments = bbsService.getHiddenCommentsByThread(thread);
        model.addAttribute("thread", thread);
        model.addAttribute("comments", comments);
        return "admin/bbs/comment/list";
    }
    
    // 削除済状態のコメントを表示
    @GetMapping("/threads/{threadId}/comments/deleted")
    public String listDeletedComments(@PathVariable Long threadId, Model model) {
        Thread thread = bbsService.getThreadById(threadId);
        List<Comment> comments = bbsService.getDeletedCommentsByThread(thread);
        model.addAttribute("thread", thread);
        model.addAttribute("comments", comments);
        return "admin/bbs/comment/list";
    }
    
    // コメントの詳細を表示
    @GetMapping("/threads/{threadId}/comments/{commentId}")
    public String showComment(@PathVariable Long threadId, 
                              @PathVariable Long commentId,
                              Model model) {
        model.addAttribute("comment", bbsService.getCommentById(commentId));
        return "admin/bbs/comment/detail";
    }
    
    // スレッドをDISPLAYに変更
    @PostMapping("threads/{threadId}/redisplay")
    public String redisplayThread(@PathVariable Long threadId, 
                                @ModelAttribute Comment comment,
                                @AuthenticationPrincipal CustomUserDetails userDetails,
                                Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        Thread existingThread = bbsService.getThreadById(threadId);
        try {
            bbsService.redisplayThread(existingThread, user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        return "redirect:/admin/bbs/threads/all";
    }
    
    // スレッドをDELETEDに変更
    @PostMapping("threads/{threadId}/remove")
    public String removeThread(@PathVariable Long threadId, 
                                @ModelAttribute Comment comment,
                                @AuthenticationPrincipal CustomUserDetails userDetails,
                                Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        Thread existingThread = bbsService.getThreadById(threadId);
        try {
            bbsService.removeThread(existingThread, user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        return "redirect:/admin/bbs/threads/all";
    }
    
    // コメントをDISPLAYに変更
    @PostMapping("threads/{threadId}/comments/{commentId}/redisplay")
    public String redisplayComment(@PathVariable Long threadId, 
                                @PathVariable Long commentId,
                                @ModelAttribute Comment comment,
                                @AuthenticationPrincipal CustomUserDetails userDetails,
                                Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        Comment existingComment = bbsService.getCommentById(commentId);
        try {
            bbsService.redisplayComment(existingComment, user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        return "redirect:/admin/bbs/threads/{threadId}/comments/all";
    }
    
    // コメントをDELETEDに変更
    @PostMapping("threads/{threadId}/comments/{commentId}/remove")
    public String removeComment(@PathVariable Long threadId, 
                                @PathVariable Long commentId,
                                @ModelAttribute Comment comment,
                                @AuthenticationPrincipal CustomUserDetails userDetails,
                                Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        Comment existingComment = bbsService.getCommentById(commentId);
        try {
            bbsService.removeComment(existingComment, user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        return "redirect:/admin/bbs/threads/{threadId}/comments/all";
    }

}
