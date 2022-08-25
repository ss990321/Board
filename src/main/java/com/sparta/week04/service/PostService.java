package com.sparta.week04.service;

import com.sparta.week04.dto.PostRequestDto;
import com.sparta.week04.dto.ResponseDto;
import com.sparta.week04.entity.Member;
import com.sparta.week04.entity.Post;
import com.sparta.week04.jwt.TokenProvider;
import com.sparta.week04.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final MemberService memberService;

    private final CommentService commentService;

    private final TokenProvider tokenProvider;


    @Transactional
    public ResponseDto<?> createPost(PostRequestDto requestDto) {


        String nickName=memberService.getMyNickname();
        Post post = new Post(requestDto, nickName);

        postRepository.save(post);

        return ResponseDto.success(post);
    }


    @Transactional(readOnly = true)
    public ResponseDto<?> getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NULL_POST_ID", "해당 아이디의 게시글이 존재하지 않습니다.");
        }

        return ResponseDto.success(optionalPost.get());
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> getAllPost() {
        return ResponseDto.success(postRepository.findAllByOrderByModifiedAtDesc());
    }

    @Transactional
    public ResponseDto<Post> updatePost(Long id, PostRequestDto requestDto) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NULL_POST_ID", "해당 아이디의 게시글이 존재하지 않습니다.");
        }

        Post post = optionalPost.get();

        String nickName=memberService.getMyNickname();

        if(!nickName.equals(post.getAuthor())){
            return ResponseDto.fail("NOT_UPDATE", "작성자만 수정할 수 있습니다.");
        }

        post.update(requestDto);

        return ResponseDto.success(post);
    }

    @Transactional
    public ResponseDto<?> deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "해당 아이디의 게시글이 존재하지 않습니다.");
        }

        String nickName=memberService.getMyNickname();


        Post post = optionalPost.get();

        if(!nickName.equals(post.getAuthor())){
            return ResponseDto.fail("NOT_DELETE", "작성자만 삭제할 수 있습니다.");
        }

        postRepository.delete(post);

        return ResponseDto.success(true);
    }


}