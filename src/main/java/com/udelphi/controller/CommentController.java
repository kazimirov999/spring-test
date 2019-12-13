package com.udelphi.controller;


import java.util.List;
import java.util.Set;
import com.udelphi.dto.CommentDto;
import com.udelphi.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a comment")
    public CommentDto createComment(
            @ApiParam(value = "Comment object store in database table", required = true)
            @RequestBody CommentDto commentDto) {
        return commentService.saveComment(commentDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a comment by Id")
    public CommentDto getComment(
            @ApiParam(value = "Comment id from which comment object will retrieve", required = true)
            @PathVariable int id) {
        return commentService.getComment(id);
    }

    @GetMapping
    @ApiOperation(value = "View a list of available comments", response = List.class)
    public List<CommentDto> getComments() {
        return commentService.getAllComments();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a comment")
    public void deleteComment(
            @ApiParam(value = "Comment Id from which comment object will delete from database table", required = true)
            @PathVariable int id) {
        commentService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a comment")
    public void updateComment(
            @ApiParam(value = "Comment Id to update comment object", required = true)
            @PathVariable int id,
            @ApiParam(value = "Update comment object", required = true)
            @RequestBody CommentDto commentDto) {
        commentService.updateComment(id, commentDto);
    }


    @GetMapping("/{id}/comments")
    @ApiOperation(value = "View a list of available comment sub comment", response = List.class)
    public Set<CommentDto> getAllSubComments(
            @ApiParam(value = "Comment Id from which sub comments objects will retrieve", required = true)
            @PathVariable int id) {
        return commentService.getSubComments(id);
    }

}
