package com.udelphi.service;

import java.util.List;
import java.util.Set;
import com.udelphi.dto.CommentDto;
import com.udelphi.exception.EntityNotFoundException;
import com.udelphi.model.Comment;
import com.udelphi.repository.CommentRepository;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    public CommentDto saveComment(CommentDto commentDto) {
        Comment saveComment = commentRepository.save(modelMapper.map(commentDto, Comment.class));
        return modelMapper.map(saveComment, CommentDto.class);
    }

    public CommentDto getComment(int id) {
        return commentRepository.findById(id)
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<CommentDto> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(toList());
    }

    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }

    public void updateComment(int id, CommentDto commentDto) {
        commentRepository.findById(id)
                .map(comment -> modelMapper.map(commentDto, Comment.class))
                .ifPresentOrElse(commentRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Entity not found with id: " + id);
                        });
    }

    public Set<CommentDto> getSubComments(int id) {
        return commentRepository.findSubComment(id)
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(toSet());
    }
}
