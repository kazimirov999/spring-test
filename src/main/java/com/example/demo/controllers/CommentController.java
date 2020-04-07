package com.example.demo.controllers;

import com.example.demo.dto.CommentDto;
import com.example.demo.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/comments")

public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @ApiOperation(value = "Get all comments")
    public Set<CommentDto> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get comment by id")
    public CommentDto getCommentById(
            @ApiParam(value = "The comment ID that will return the comment from the database table")
            @PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a comment")
    public CommentDto addComment(
            @ApiParam(value = "Comment object store in database table", required = true)
            @RequestBody CommentDto comment) {
        return commentService.saveComment(comment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete comment by id")
    public void deleteCommentDyId(
            @ApiParam(value = "Comment ID to delete the comment from the database table")
            @PathVariable Integer id) {
        commentService.deleteCommentById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all comments")
    public void deleteCommentAll() {
        commentService.deleteCommentAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update comment by id")
    public void updateCommentById(
            @ApiParam(value = "Comment ID to be updated in the database table")
            @PathVariable Integer id,
            @ApiParam(value = "Update comment object")
            @RequestBody CommentDto commentDto) {
        commentService.updateComment(id, commentDto);
    }
}
