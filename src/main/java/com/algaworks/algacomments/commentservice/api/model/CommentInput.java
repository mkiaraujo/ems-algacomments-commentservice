package com.algaworks.algacomments.commentservice.api.model;

import lombok.Data;

@Data
public class CommentInput {
    private String text;
    private String author;
}
