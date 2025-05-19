package com.algaworks.algacomments.commentservice.api.controller;

import com.algaworks.algacomments.commentservice.api.model.CommentInput;
import com.algaworks.algacomments.commentservice.api.model.CommentOutput;
import com.algaworks.algacomments.commentservice.common.IdGenerator;
import com.algaworks.algacomments.commentservice.domain.model.Comment;
import com.algaworks.algacomments.commentservice.domain.model.CommentId;
import com.algaworks.algacomments.commentservice.domain.repository.CommentRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentOutput create(@RequestBody CommentInput input) {
        Comment comment = Comment.builder()
                .id(new CommentId(IdGenerator.generateTSID()))
                .text(input.getText())
                .author(input.getAuthor())
                .createdAt(OffsetDateTime.now())
                .build();

        comment = commentRepository.saveAndFlush(comment);

        return commentToCommentOutput(comment);

    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentOutput buscar(@PathVariable TSID commentId) {
        Comment comment = commentRepository.findById(new CommentId(commentId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return commentToCommentOutput(comment);
    }

    @GetMapping
    public Page<CommentOutput> listar(@PageableDefault Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(this::commentToCommentOutput);
    }

    private CommentOutput commentToCommentOutput(Comment comment) {
        return CommentOutput.builder()
                .id(comment.getId().getValue())
                .text(comment.getText())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}

