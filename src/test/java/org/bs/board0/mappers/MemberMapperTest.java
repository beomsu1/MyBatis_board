package org.bs.board0.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.bs.board0.dto.MemberReadDTO;
import org.bs.board0.dto.MemberRegistDTO;
import org.junit.jupiter.api.Test;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // // MemberRegist
    // @Test
    // @Transactional
    // @Commit
    // public void MemberRegistTest(){

    //     String email = "beomsu_1221@naver.com";
    //     String mpw = passwordEncoder.encode("1111");
    //     String mname = "beomsu";

    //     String rolename = "ADMIN";
    //     String rolename1 = "USER";

    //     MemberRegistDTO memberRegistDTO = new MemberRegistDTO(email, mpw, mname);
    //     MemberRoleRegistDTO memberRoleRegistDTO = new MemberRoleRegistDTO(email, rolename);
    //     MemberRoleRegistDTO memberRoleRegistDTO2 = new MemberRoleRegistDTO(email, rolename1);

    //     memberMapper.memberRegist(memberRegistDTO);
    //     memberMapper.memberRoleRegist(memberRoleRegistDTO);
    //     memberMapper.memberRoleRegist(memberRoleRegistDTO2);

    //     log.info("Member Regist finish");

    // }

    // memberselect
    @Test
    public void memberSelectTest(){

        String email = "beomsu_1221@naver.com";

        MemberReadDTO result = memberMapper.memberSelect(email);

        log.info(result);
    }


        // MemberRegist
    @Test
    @Transactional
    @Commit
    public void MemberRegistTest1(){

        MemberRegistDTO memberRegistDTO = MemberRegistDTO.builder()
        .email("9805121234@naver.com")
        .mpw(passwordEncoder.encode("1111"))
        .mname("beomsu1")
        .rolename("USER")
        .build();

        memberMapper.memberRegist(memberRegistDTO);
        memberMapper.memberRoleRegist(memberRegistDTO);

        log.info("Member Regist finish");

    }



}
