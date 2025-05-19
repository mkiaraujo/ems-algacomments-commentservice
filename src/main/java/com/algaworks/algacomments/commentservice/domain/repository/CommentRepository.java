package com.algaworks.algacomments.commentservice.domain.repository;

import com.algaworks.algacomments.commentservice.domain.model.Comment;
import com.algaworks.algacomments.commentservice.domain.model.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {
}
