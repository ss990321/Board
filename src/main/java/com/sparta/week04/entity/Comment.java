package com.sparta.week04.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week04.dto.comment.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;

    @JsonIgnore
    @Column(nullable = false)
    private Long postId;

    @Column(nullable = true)
    private String author;

    @Column(nullable = false)
    private String content;

    public Comment(CommentRequestDto commentDto, String nickName) {
        this.postId=commentDto.getPostId();
        this.author=nickName;
        this.content=commentDto.getContent();
    }

    public void update(CommentRequestDto requestDto) {
        this.content=requestDto.getContent();
    }

}
