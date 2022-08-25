package com.sparta.week04.service;

import com.sparta.week04.dto.*;
import com.sparta.week04.dto.member.LoginRequestDto;
import com.sparta.week04.dto.member.MemberNameResponseDto;
import com.sparta.week04.dto.member.MemberRequestDto;
import com.sparta.week04.dto.member.MemberResponseDto;
import com.sparta.week04.dto.token.TokenDto;
import com.sparta.week04.entity.Member;
import com.sparta.week04.entity.RefreshToken;
import com.sparta.week04.jwt.TokenProvider;
import com.sparta.week04.repository.MemberRepository;
import com.sparta.week04.repository.RefreshTokenRepository;
import com.sparta.week04.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ResponseDto<?> signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByNickname(memberRequestDto.getNickname())) {
            return ResponseDto.fail("DUPLICATE_NICKNAME", "중복된 닉네임입니다.");
        }

        if(!memberRequestDto.getPassword().equals(memberRequestDto.getPasswordConfirm())){
            return ResponseDto.fail("PASSWORD_NOT_MATCHED","비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        memberRepository.save(member);
        return ResponseDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .nickname(member.getNickname())
                        .createdAt(member.getCreatedAt())
                        .modifiedAt(member.getModifiedAt())
                        .build()
        );
    }

    @Transactional
    public ResponseDto<?> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = loginRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        Optional<Member> optionalMember = memberRepository.findByNickname(loginRequestDto.getNickname());

        Member member = optionalMember.get();

//        if (!memberRepository.existsByNickname(loginRequestDto.getNickname())) {
//            return ResponseDto.fail("NOT_FOUND", "사용자를 찾을 수 없습니다.");
//        }
//        if(!loginRequestDto.getPassword().equals(member.getPassword())){
//            return ResponseDto.fail("NOT_FOUND", "사용자를 찾을 수 없습니다.");
//        }


        setTokenHeader(response, tokenDto);

        return ResponseDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .nickname(member.getNickname())
                        .createdAt(member.getCreatedAt())
                        .modifiedAt(member.getModifiedAt())
                        .build()
        );
    }

    private void setTokenHeader(HttpServletResponse response, TokenDto tokenDto){
        response.addHeader("Access-Token", tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public String getMyNickname() {
        Optional<Member> optionalMember = memberRepository.findById(SecurityUtil.getCurrentMemberId());

        Member member = optionalMember.get();
        return member.getNickname();
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberNameResponseDto getMyname() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberNameResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }


}