package org.bs.board0.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService{

    // BCryptPasswordEncoder를 사용하여 비밀번호를 암호화
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 사용자의 정보를 가져오는 역할을 하는 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("-------------------------");
        log.info("loadUserByUsername: " + username);
        log.info("-------------------------");

        // 유저의 아이디와 비밀번호 생성, 권한 부여
        UserDetails user = User.builder()
        .username(username)
        .password(passwordEncoder.encode("1111"))
        .authorities("ROLE_USER")
        .build();

        return user;
    }
    
}
