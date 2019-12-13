package com.udelphi.repository;

import java.util.Set;
import com.udelphi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c.comments from Comment c where c.id = :id")
    Set<Comment> findSubComment(int id);
    Set<Comment> findAllByProductId(int id);
}
