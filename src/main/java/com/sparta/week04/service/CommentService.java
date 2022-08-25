package com.sparta.week04.service;

import com.sparta.week04.dto.ResponseDto;
import com.sparta.week04.dto.comment.CommentRequestDto;
import com.sparta.week04.entity.Comment;
import com.sparta.week04.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final MemberService memberService;

    @org.springframework.transaction.annotation.Transactional
    public ResponseDto<?> createComment(CommentRequestDto requestDto) {

        String nickName = memberService.getMyNickname();
        Comment comment = new Comment(requestDto, nickName);

        commentRepository.save(comment);

        return ResponseDto.success(comment);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public ResponseDto<?> getComment(Long id) {
        List<Comment> Comment = commentRepository.findAllByPostId(id);

        if (Comment.isEmpty()) {
            return ResponseDto.fail("NULL_COMMENT_ID", "해당 아이디의 댓글이 존재하지 않습니다.");
        }

        return ResponseDto.success(Comment);
    }


    @org.springframework.transaction.annotation.Transactional
    public ResponseDto<Comment> updateComment(Long id, CommentRequestDto requestDto) {
        Optional<Comment> optionalComment = commentRepository.findAllByPostIdAndId(id, requestDto.getPostId());

        if (optionalComment.isEmpty()) {
            return ResponseDto.fail("NULL_COMMENT_ID", "해당 아이디의 댓글이 존재하지 않습니다.");
        }

        String nickName=memberService.getMyNickname();


        Comment comment = optionalComment.get();

        if(!nickName.equals(comment.getAuthor())){
            return ResponseDto.fail("NOT_UPDATE", "작성자만 수정할 수 있습니다.");
        }

        comment.update(requestDto);

        return ResponseDto.success(comment);
    }

    @org.springframework.transaction.annotation.Transactional
    public ResponseDto<?> deleteComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        String nickName = memberService.getMyNickname();

        if (optionalComment.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "댓글이 존재하지 않습니다.");
        }


        Comment comment = optionalComment.get();

        if(!nickName.equals(comment.getAuthor())){
            return ResponseDto.fail("NOT_DELETE", "작성자만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);

        return ResponseDto.success(true);
    }

}
