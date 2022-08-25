package com.sparta.week04.dto.member;

import com.sparta.week04.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberNameResponseDto {
    private String nickname;

    public static MemberNameResponseDto of(Member member) {
        return new MemberNameResponseDto(member.getNickname());
    }
}
