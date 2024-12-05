package org.zerock.ex3.member.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex3.member.dto.MemberDto;
import org.zerock.ex3.member.entity.MemberEntity;
import org.zerock.ex3.member.exception.MemberExceptions;
import org.zerock.ex3.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public MemberDto read(String mid, String mpw) {
        Optional<MemberEntity> result = memberRepository.findById(mid);
        MemberEntity memberEntity = result.orElseThrow(MemberExceptions.BAD_CREDENTIALS::get);

        if (!passwordEncoder.matches(mpw, memberEntity.getMpw())) {
            throw MemberExceptions.BAD_CREDENTIALS.get();
        }
        return new MemberDto(memberEntity);

    }

}
