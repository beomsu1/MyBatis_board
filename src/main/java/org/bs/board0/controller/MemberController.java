package org.bs.board0.controller;

import org.bs.board0.dto.MemberRegistDTO;
import org.bs.board0.service.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member/")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    
    // 로그인 페이지
    @PreAuthorize("permitAll")
    @GetMapping("login")
    public void getLoginPage(){

        log.info("GET | loginPage");
    }

    // 회원가입 페이지
    @GetMapping("signup")
    public void getSignupPage(){

        log.info("GET | SignupPage");
    }

    // 회원가입
    @PostMapping("signup")
    public String postSignup(MemberRegistDTO memberRegistDTO){

        memberService.memberRegist(memberRegistDTO);

        return "redirect:/member/login";
    }

    // 마이 페이지
    @GetMapping("mypage")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void getMypage(){

        log.info("GET | Mypage");
    }

    // 수정 페이지
    @GetMapping("modify")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void getModifyPage(){

        log.info("GET | getModifyPage");
    }
    
    // 수정

    // 탈퇴

    // 로그아웃
    @PostMapping("logout")
    public String PostLogout(){

        log.info("POST | PostLogout");

        return "redirect:/member/login";
    }
}
