package com.kafein.intern.postinger_comment_service.model;

import lombok.Data;


import javax.persistence.*;
@Data
@Entity
@Table(name = "COMMENT")

public class Comment  {

    @Id
    @Column(name = "ID")
    private String commentId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "text")
    private String text;

    @Column(name = "post_id")
    private String postId;

}
