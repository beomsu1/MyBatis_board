package org.bs.board0.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class MemberDTO extends User implements OAuth2User {

    // 이름
    private String mname;
    private String email;
    private String pw;

    // 생성자 생성 - 항상 부모클래스의 생성자를 호출 해야함
    public MemberDTO(String email, String mpw, String mname, List<String> rolenames) {
        super(email, mpw,
                rolenames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));

        this.mname = mname;
        this.email = email;
        this.pw = mpw;
    }

    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    // 실제 사용이름 반환 역할 -> email 반환 해야함
    @Override
    public String getName() {

        // email 반환하려면 memberDTO에 email 추가
        return email;
    }

}
