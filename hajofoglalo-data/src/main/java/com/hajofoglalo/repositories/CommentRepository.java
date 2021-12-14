package com.hajofoglalo.repositories;

import com.hajofoglalo.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
