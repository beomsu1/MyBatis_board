package org.bs.board0.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration // 설정 파일 대신에 사용하겠다! 라는 어노테이션
@Log4j2
public class CustomSecurityConfig {

    // @Bean -> 스프링 프레임워크의 핵심 개념 중 하나로, 코드의 구조화와 관리, 의존성 주입, 테스트 용이성,
    // 스프링의 다양한 기능 활용을 위해 중요한 역할

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
        //http.formLogin(Customizer.withDefaults());

        // 로그인 경로
        http.formLogin(config -> {

            // 로그인 페이지 경로 설정
            config.loginPage("/member/login");
        });

        // // Login이 안되면 페이지 못넘어가게 막고 다시 Login창으로 보내는 코드
        // http.authorizeHttpRequests(requests -> {

        //     requests.anyRequest().authenticated();

        // });

        return http.build();
    }

}
