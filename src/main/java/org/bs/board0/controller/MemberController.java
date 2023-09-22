package org.bs.board0.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.bs.board0.dto.MemberModifyDTO;
import org.bs.board0.dto.MemberRegistDTO;
import org.bs.board0.service.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public void getLoginPage() {

        log.info("GET | loginPage");
    }

    // 회원가입 페이지
    @GetMapping("signup")
    public void getSignupPage() {

        log.info("GET | SignupPage");
    }

    // 회원가입
    @PostMapping("signup")
    public String postSignup(MemberRegistDTO memberRegistDTO) {

        memberService.memberRegist(memberRegistDTO);

        return "redirect:/member/login";
    }

    // 마이 페이지
    @GetMapping("mypage")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void getMypage(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        log.info("GET | Mypage");

        model.addAttribute("userDetails", userDetails);

        // 사용자의 권한(롤)에서 "ROLE_" 접두사를 제거하여 수정
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(authority -> authority.replace("ROLE_", ""))
                .collect(Collectors.toList());
        model.addAttribute("userAuthorities", authorities);
    }

    // 수정 페이지
    @GetMapping("modify")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void getModifyPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        log.info("GET | getModifyPage");

        model.addAttribute("userDetails", userDetails);
    }

    // 수정
    @PostMapping("modify")
    public String PostMemberModify(MemberModifyDTO memberModifyDTO) {

        log.info("POST | PostMemberModify");

        memberService.memberModify(memberModifyDTO);

        return "redirect:/member/mypage";
    }

    // 탈퇴
    @PostMapping("delete")
    public String PostMemberDelete(String email) {

        log.info("POST | PostMemberDelete");

        memberService.memberDelete(email);

        return "redirect:/member/login";
    }

    // 로그아웃
    @PostMapping("logout")
    public String PostLogout() {

        log.info("POST | PostLogout");

        return "redirect:/member/login";
    }
}
