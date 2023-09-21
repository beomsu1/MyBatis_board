package org.bs.board0.security;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bs.board0.dto.MemberDTO;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2

// OAuth2 사용자 서비스를 사용하기 위해 필요한 기본 구현을 상속
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    @Override

    // loadUser -> OAuth2 로그인을 수행할 때 호출되는 메서드
    // OAuth2User -> OAuth2.0 기반의 인증을 통해 얻은 사용자 정보를 나타내는 인터페이스이다.
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("-------------------");
        log.info("userRequest" + userRequest);
        log.info("-------------------");

        // ClientRegistration
        // SS는 OAuth 2.0 인증을 구성하고 사용자가 로그인하려는 서비스 또는 애플리케이션에 필요한 클라이언트 등록 정보를 알고 있게 됨
        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        // 클라이언트이름 가져오기
        String clientName = clientRegistration.getClientName();
        log.info("Client Name = " + clientName);

        // 부모 클래스에서 상속된 메서드를 호출하여, userRequest를 사용해 OAuth2.0 기반의 사용자 정보를 가져옴
        // userRequest는 OAuth2.0 인증 요청에 대한 정보를 담고있음
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // OAuth 2.0 인증에서 반환된 사용자의 속성을 가져옵니다. Map형식으로 받음
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        // email값 초기화
        String email = null;

        // clientName을 이용해 switch문 사용
        switch (clientName) {
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }

        log.info("email : " + email);

        // memberDTO 만들기
        MemberDTO memberDTO = new MemberDTO(email, "", "Kakao Login", List.of("USER"));

        // memberDTO 반환 -> 반환하려면 MemberDTO에 OAuth2USER가 implements
        return memberDTO;

    }

    // getKakaoEmail 정의
    private String getKakaoEmail(Map<String, Object> paramMap){

        log.info("KAKAO----------");

        // "kakao_account" 값 가져와서 value라는 변수에 저장
        Object value = paramMap.get("kakao_account");
        log.info(value);

        // JSON -> LinkedHashMap으로 파싱
        LinkedHashMap accountMap = (LinkedHashMap) value;

        // LinkedHashMap 형태인 acoountMap 에서 email 값 가져오기 (String 값으로 변환)
        String email = (String) accountMap.get("email");

        log.info("email: " + email);

        // email 반환
        return email;

    }

}
