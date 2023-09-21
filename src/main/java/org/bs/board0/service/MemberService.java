package org.bs.board0.service;

import org.bs.board0.dto.MemberRegistDTO;
import org.bs.board0.dto.MemberModifyDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    // member Regist
    void memberRegist(MemberRegistDTO memberRegistDTO);

    // member infomation modify
    void memberModify(MemberModifyDTO memberModifyDTO);

    // member delete
    int memberDelete(String email);
    
}
