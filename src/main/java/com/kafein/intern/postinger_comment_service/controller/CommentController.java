package com.kafein.intern.postinger_comment_service.controller;

import com.kafein.intern.postinger_comment_service.model.Comment;
import com.kafein.intern.postinger_comment_service.service.CommentService;
import com.kafein.intern.postinger_comment_service.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final JwtUtil jwtUtil;



    @GetMapping("/find-all")
    public ResponseEntity<List<Comment>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/find")
    public ResponseEntity<Comment> findById(@RequestParam (name = "commentId") String commentId){
        return ResponseEntity.ok(commentService.findById(commentId));
    }

    @GetMapping("/find-post-comment/{postId}")
    public ResponseEntity<List<Comment>> findCommentByPostId(@PathVariable (name= "postId") String postId){
        return  ResponseEntity.ok(commentService.findAllByPostId(postId));
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Comment comment, HttpServletRequest request){
        System.out.println(request);
        String jwt =commentService.getToken(request);
        Long userId = jwtUtil.extractId(jwt);
        if(comment.getCommentId() == null && comment.getUserId() == null){
            comment.setUserId(userId);
            Comment savedComment = commentService.saveOrUpdate(comment);
            return new ResponseEntity<>("Product is created successfully",HttpStatus.CREATED);//test yazarken normalde sadece body bastıran ve 200 veren kodu bu şekilde değiştirdim.
        }
        else throw new RuntimeException("ID and UserID must be null");

    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String commentId,HttpServletRequest request){
        String jwt =commentService.getToken(request);
        Long userId = jwtUtil.extractId(jwt);
        if(commentId != null){
            if(commentService.findById(commentId).getUserId().equals(userId)){
                commentService.delete(commentId);
                return ResponseEntity.ok("Post deleted!");
            }else throw new RuntimeException("You can delete only your own posts");
        }else throw new RuntimeException("Post id must not be null");
    }
}

