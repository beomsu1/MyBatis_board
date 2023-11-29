package org.bs.board0.config;

import javax.sql.DataSource;

import org.bs.board0.security.handler.CustomAccessDeniedHandler;
import org.bs.board0.security.handler.CustomOAuthSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration // 설정 파일 대신에 사용하겠다! 라는 어노테이션
@Log4j2
@RequiredArgsConstructor
public class CustomSecurityConfig {

    // 데이터베이스와의 연결을 관리하는 인터페이스
    private final DataSource dataSource;

    // @Bean -> 스프링 프레임워크의 핵심 개념 중 하나로, 코드의 구조화와 관리, 의존성 주입, 테스트 용이성,
    // 스프링의 다양한 기능 활용을 위해 중요한 역할

    // Remember Me 기능
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){

        // 사용자의 로그인 정보를 데이터베이스에 저장하고 관리하는데 사용
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();

        // 데이터베이스 연결 정보를 설정 후 repo를 반환
        repo.setDataSource(dataSource);
        return repo;
    }

    // BCryptPasswordEncoder 사용하여 passwordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // 필터 체인 설정
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {

        log.info("-----------------------configuration---------------------");

        // 기본 로그인 페이지
        // http.formLogin(Customizer.withDefaults());

        // 로그인 경로
        http.formLogin(config -> {

            // 로그인 페이지 경로 설정
            config.loginPage("/member/login")

            // 로그인 성공 시 리다이렉트
            .defaultSuccessUrl("/board/list", true);
        });

        // csrf 토큰 비활성화
        http.csrf(config -> {
            config.disable();
        });
        
        // 자동 로그인
        http.rememberMe(config ->{

            // remeberMe 기능을 사용하기 위해 persistentTokenRepository 설정
            config.tokenRepository(persistentTokenRepository());

            // 토근 시간 설정
            config.tokenValiditySeconds(60*60);

        });

        // AccessDeniedHandler 설정
        http.exceptionHandling(config ->{
            config.accessDeniedHandler(new CustomAccessDeniedHandler());
        });

        //oauth2 login
        http.oauth2Login(config -> {

            // 로그인 페이지 설정
            config.loginPage("/member/login");

            // 핸들러 설정
            config.successHandler(new CustomOAuthSuccessHandler());
        });

        return http.build();
    }

}
