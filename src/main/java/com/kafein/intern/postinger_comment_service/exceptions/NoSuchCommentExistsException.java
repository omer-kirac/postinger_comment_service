package com.kafein.intern.postinger_comment_service.exceptions;

public class NoSuchCommentExistsException extends RuntimeException{
    private String message;

    public NoSuchCommentExistsException(String msg){
        super(msg);
        this.message = msg;
    }
}
