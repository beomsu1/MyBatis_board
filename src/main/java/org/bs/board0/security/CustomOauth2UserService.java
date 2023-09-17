package org.bs.board0.security;

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
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("-------------------");
        log.info("userRequest" + userRequest);
        log.info("-------------------");

        return super.loadUser(userRequest);
    }

}
