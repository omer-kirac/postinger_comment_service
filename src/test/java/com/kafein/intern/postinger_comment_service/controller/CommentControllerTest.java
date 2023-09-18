package com.kafein.intern.postinger_comment_service.controller;

import com.kafein.intern.postinger_comment_service.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentControllerTest extends AbstractTest{

    public Comment getComment(){
        Comment comment = new Comment();
        comment.setCommentId(null);
        comment.setPostId("akspmdkasd");
        comment.setUserId(null);
        comment.setText("asdmasöasl");
        return comment;
    }

    @Test
    public void testFindAll() throws Exception {
        setUp();
        String uri = "/comments/find-all";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
        String content = mvcResult.getResponse().getContentAsString();
        Comment[] commentList = super.mapFromJson(content,Comment[].class);
        assertTrue(commentList.length>0);
    }

    @Test
    public void createComment() throws Exception {
        setUp();
        String uri = "/comments/save";
        Comment comment = getComment();

        String inputJson = super.mapToJson(comment);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Product is created successfully");
    }

    @Test // Çalışmıyor pathvariable yüzünden
    void findCommentByPostId() throws Exception {
        setUp();
        String uri = "/find-post-comment/{postId}";
        Comment comment = getComment();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/find-post-comment/{postId}","akspmdkasd")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
        String content = mvcResult.getResponse().getContentAsString();
        Comment[] commentsList = super.mapFromJson(content,Comment[].class);
        assertTrue(commentsList.length > 0);
    }


    @Test
    void deleteComment() throws Exception {
    }
}