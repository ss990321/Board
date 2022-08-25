package com.sparta.week04.entity;


import com.sparta.week04.dto.PostRequestDto;
import com.sparta.week04.dto.ResponseDto;
import com.sparta.week04.service.CommentService;
import lombok.*;
import org.hibernate.validator.constraints.time.DurationMax;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = true)
    private String author;


    public Post(PostRequestDto postRequestDto, String nickName) {
        this.title = postRequestDto.getTitle();
        this.author= nickName;
        this.content = postRequestDto.getContent();
    }


    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }


}




