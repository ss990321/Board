package com.sparta.week04.controller;

import com.sparta.week04.dto.ResponseDto;
import com.sparta.week04.dto.comment.CommentRequestDto;
import com.sparta.week04.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/api/auth/comment")
    public ResponseDto<?> createComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }

    @GetMapping("/api/comment/{id}")
    public ResponseDto<?> getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }


    @PutMapping("/api/auth/comment/{id}")
    public ResponseDto<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }

    @DeleteMapping("/api/auth/comment/{id}")
    public ResponseDto<?> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
