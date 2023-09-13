package org.bs.board0.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class MemberDTO extends User {

    // 이름
    private String mname;

    // 생성자 생성 - 항상 부모클래스의 생성자를 호출 해야함
    public MemberDTO(String email, String mpw, String mname, List<String> rolenames) {
        super(email, mpw,
                rolenames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));

        this.mname = mname;
    }

}
