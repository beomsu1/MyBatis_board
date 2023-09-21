package org.bs.board0.security.handler;

import java.io.IOException;

import org.bs.board0.dto.MemberDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
// AuthenticationSuccessHandler -> 사용자 인증 성공 시 수행할 작업을 정의하는 인터페이스
public class CustomOAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    // onAuthenticationSuccess -> 사용자가 성공적으로 인증되면 호출되는 메서드
    // HttpServletRequest request -> 현재 요청과 관련된 HTTP 요청 객체, 요청에 대한 정보를 얻거나 조작 가능
    // HttpServletResponse response -> 현재 요청과 관련된 HTTP 응답 객체, 응답을 조작하거나 사용자에게 리다이렉션
    // 보내기 가능
    // Authentication authentication -> 사용자의 인증 정보를 나타냄, 사용자가 누구인지, 어떤 권한을 가지고 있는지
    // 정보 포함
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // 사용자의 주요(principal) 정보를 가져오는 메서드
        log.info("information: " + authentication.getPrincipal());
        log.info("-----------------");

        // MemberDTO 형식으로 주요정보 받기
        MemberDTO dto = (MemberDTO) authentication.getPrincipal();

        log.info("MemberDTO.information: " + dto);
        log.info("----------------");

        // 비밀번호가 null, 빈 공백일 시 수정페이지로 이동
        if (dto.getPw() == null || dto.getPw().equals("")) {
            response.sendRedirect("/member/modify");
        }

    }

}
