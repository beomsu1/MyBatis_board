package org.bs.board0.service;

import org.bs.board0.dto.MemberModifyDTO;
import org.bs.board0.dto.MemberRegistDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;


@SpringBootTest
@Log4j2
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;


    // memberRegist
    @Test
    @Transactional
    @Commit
    public void memberRegistServceTest(){

        MemberRegistDTO memberRegistDTO = MemberRegistDTO.builder()
        .email("bs1@naver.com")
        .mpw("123")
        .mname("범수")
        .rolename("USER")
        .build();

        memberService.memberRegist(memberRegistDTO);

        log.info("memberRegistServceTest---------------------");
    }
    
    // member information modify
    @Test
    @Transactional
    @Commit
    public void memberModifyServiceTest(){

        MemberModifyDTO memberModifyDTO = MemberModifyDTO.builder()
        .email("bs1@naver.com")
        .mpw("123123")
        .mname("beomsu1111")
        .build();

        memberService.memberModify(memberModifyDTO);

        log.info("memberModifyServiceTest------------------");
    }

    // member Delete
    @Test
    @Transactional
    @Commit
    public void memberDeleteServiceTest(){

        String email = "bs1@naver.com";

        memberService.memberDelete(email);

        log.info("memberDeleteServiceTest---------------");
    }
}
