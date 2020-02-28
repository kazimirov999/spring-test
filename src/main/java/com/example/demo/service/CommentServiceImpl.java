package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;


    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    public CommentDto saveComment(CommentDto commentDto) {
        Comment persist = commentRepository.save(modelMapper.map(commentDto, Comment.class));
        return modelMapper.map(persist, CommentDto.class);
    }

    public Set<CommentDto> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toSet());
    }

    public CommentDto getCommentById(Integer id) {
        return commentRepository.findById(id)
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .orElseThrow(()-> new EntityNotFoundException("Comment is not found") );
    }

    public void updateComment(Integer id, CommentDto commentDto) {
        commentRepository.findById(id)
                .map(comment -> modelMapper.map(commentDto, Comment.class))
                .ifPresentOrElse(commentRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Comment not found by id " + id);
                        });
    }

    public void deleteCommentById(Integer id) {
        commentRepository.deleteById(id);
    }

    public void deleteCommentAll() {
        commentRepository.deleteAll();
    }

}
