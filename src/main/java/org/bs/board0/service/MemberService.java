package org.bs.board0.service;

import org.bs.board0.dto.MemberRegistDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    // member Regist
    void memberRegist(MemberRegistDTO memberRegistDTO);
    
}
