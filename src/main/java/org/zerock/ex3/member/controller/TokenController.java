package org.zerock.ex3.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.ex3.member.dto.MemberDto;
import org.zerock.ex3.member.security.util.JWTUtil;
import org.zerock.ex3.member.service.MemberService;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/token")
@Log4j2
@RequiredArgsConstructor
public class TokenController {

    private final MemberService memberService;

    private final JWTUtil jwtUtil;

    @PostMapping("/make")
    public ResponseEntity<Map<String, String>> makeToken(@RequestBody MemberDto memberDto) {

        log.info("make token ..........");

        MemberDto memberDtoResult = memberService.read(memberDto.getMid(), memberDto.getMpw());
        log.info(memberDtoResult);

        String mid = memberDtoResult.getMid();

        Map<String, Object> dateMap = memberDtoResult.getDataMap();

        String accessToken = jwtUtil.createToken(dateMap, 10);

        String refreshToken = jwtUtil.createToken(Map.of("mid", mid), 60 * 24 * 7);

        log.info("acessToken: " + accessToken);
        log.info("refreshToken: " + refreshToken);

        return null;
    }
}
