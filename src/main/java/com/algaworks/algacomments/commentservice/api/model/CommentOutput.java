package com.algaworks.algacomments.commentservice.api.model;

import com.algaworks.algacomments.commentservice.domain.model.CommentId;
import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class CommentOutput {
    private TSID id;
    private String text;
    private String author;
    private OffsetDateTime createdAt;
}
