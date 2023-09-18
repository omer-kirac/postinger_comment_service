package com.kafein.intern.postinger_comment_service.repository;

import com.kafein.intern.postinger_comment_service.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository underTest;

    public Comment getComment(){
        Comment comment = new Comment();
        comment.setCommentId("akldmasklşdmöa");
        comment.setPostId("akspmdkasd");
        comment.setUserId(1L);
        comment.setText("asdmasöasl");
        return comment;
    }


    @Test
    public void findAllByPostId() {
        //given
        Comment comment = getComment();
        underTest.save(comment);
        List<Comment> result = new ArrayList<>();
        underTest.findAllByPostId(comment.getPostId()).forEach(e -> result.add(e));
        assertEquals(result.size(),1);
        //when

    }

    @Test
    public void testFindId(){
        Comment comment = getComment();
        underTest.save(comment);
        Comment result = underTest.findById(comment.getCommentId()).get();
        assertEquals(comment.getCommentId(),result.getCommentId());
    }

    @Test
    public void testFindAll(){
        Comment comment = getComment();
        underTest.save(comment);
        List<Comment> result = new ArrayList<>();
        underTest.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(),7);//database'te kaç tane comment varsa o sayı olmalı
    }

    @Test
    public void testSave(){
        Comment comment = getComment();
        underTest.save(comment);
        Comment found = underTest.findById(comment.getCommentId()).get();
        assertEquals(comment.getCommentId(),found.getCommentId());
    }

    @Test
    public void testDelete(){
        Comment comment = getComment();
        underTest.save(comment);
        underTest.deleteById(comment.getCommentId());
        List<Comment> result = new ArrayList<>();
        underTest.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(),6);//database'teki veri sayısından bir eksik olmalı
    }
}