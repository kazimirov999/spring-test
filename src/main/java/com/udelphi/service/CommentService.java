package com.udelphi.service;

import java.util.List;
import java.util.Set;
import com.udelphi.dto.CommentDto;

public interface CommentService {
    CommentDto saveComment(CommentDto commentDto);

    CommentDto getComment(int id);

    List<CommentDto> getAllComments();

    void deleteById(int id);

    void updateComment(int id, CommentDto commentDto);

    Set<CommentDto> getSubComments(int id);
}
