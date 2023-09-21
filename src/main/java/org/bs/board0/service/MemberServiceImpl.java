package org.bs.board0.service;

import org.bs.board0.dto.MemberRegistDTO;
import org.bs.board0.mappers.MemberMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void memberRegist(MemberRegistDTO memberRegistDTO) {

        memberMapper.memberRegist(MemberRegistDTO.builder()
        .email(memberRegistDTO.getEmail())
        .mpw(passwordEncoder.encode(memberRegistDTO.getMpw()))
        .mname(memberRegistDTO.getMname())
        .build());
        memberMapper.memberRoleRegist(memberRegistDTO);

        log.info("memberRegistService ---------------------");

    }



}
