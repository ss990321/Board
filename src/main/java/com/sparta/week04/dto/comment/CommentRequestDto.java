package com.sparta.week04.dto.comment;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    @NotNull
    private Long postId;

    @NotNull
    private String content;
}