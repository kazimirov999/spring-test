package com.example.demo.service;

import com.example.demo.dto.CommentDto;

import java.util.List;
import java.util.Set;

public interface CommentService {

    CommentDto saveComment(CommentDto commentDto);

    Set<CommentDto> getAllComments();

    CommentDto getCommentById(Integer id);

    void updateComment(Integer id, CommentDto commentDto);

    void deleteCommentById(Integer id);

    void deleteCommentAll();

}
