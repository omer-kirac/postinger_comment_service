package com.kafein.intern.postinger_comment_service.service;

import com.kafein.intern.postinger_comment_service.exceptions.NoSuchCommentExistsException;
import com.kafein.intern.postinger_comment_service.model.Comment;
import com.kafein.intern.postinger_comment_service.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentService{

    private final CommentRepository commentRepository;

    public String getToken(HttpServletRequest request){
        String jwt = null;
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        if (cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("jwtToken")){
                    jwt = cookie.getValue();
                    System.out.println(jwt);
                    break;
                }
            }
        }return jwt;
    }
    public List<Comment> findAll(){
        return commentRepository.findAll();
    }


    public Comment findById(String commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> findAllByPostId(String postId){
        return commentRepository.findAllByPostId(postId);
    }

    public Comment saveOrUpdate(Comment comment){
        if(comment.getCommentId() == null){
            comment.setCommentId(UUID.randomUUID().toString());
        }
        return commentRepository.save(comment);
    }

    public void delete(String commentId){
        commentRepository.deleteById(commentId);
    }




}
