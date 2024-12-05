package org.zerock.ex3.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex3.member.entity.MemberEntity;
import org.zerock.ex3.member.exception.MemberExceptions;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testInsert() throws Exception {
        for (int i = 1; i <= 100; i++) {

            MemberEntity memberentity = MemberEntity.builder()
                    .mid("user" + i)
                    .mpw(passwordEncoder.encode("1111"))
                    .mname("USER" + i)
                    .email("user" + i + "@aaa.com")
                    .role(i <= 80 ? "USER" : "ADMIN")
                    .build();

            memberRepository.save(memberentity);
        }
    }

    @Test
    public void testRead() {
        String mid = "user1";
        Optional<MemberEntity> result = memberRepository.findById(mid);

        MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);

        System.out.println(memberEntity);

    }

    @Test
    @Transactional
    @Commit
    void testUpdate() throws Exception{
        // given
        String mid = "user1";
        Optional<MemberEntity> result = memberRepository.findById(mid);
        // when
        MemberEntity memberEntity = result.orElseThrow(MemberExceptions.NOT_FOUND::get);
        // then
        memberEntity.changePassword(passwordEncoder.encode("2222"));
        memberEntity.changeName("USER1-1");

    }


}