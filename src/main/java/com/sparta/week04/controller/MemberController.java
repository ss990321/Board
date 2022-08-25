package com.sparta.week04.controller;

import com.sparta.week04.dto.*;
import com.sparta.week04.dto.member.LoginRequestDto;
import com.sparta.week04.dto.member.MemberNameResponseDto;
import com.sparta.week04.dto.member.MemberRequestDto;
import com.sparta.week04.dto.member.MemberResponseDto;
import com.sparta.week04.dto.token.TokenDto;
import com.sparta.week04.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.signup(memberRequestDto);
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return memberService.login(loginRequestDto, response);
    }


    @GetMapping("/me")
    public ResponseEntity<MemberNameResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyname());
    }

    @GetMapping("/myName")
    public String getMyNickName() {
        return memberService.getMyNickname();
    }


}
