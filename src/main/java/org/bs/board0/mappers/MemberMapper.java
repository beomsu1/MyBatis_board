package org.bs.board0.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.bs.board0.dto.MemberReadDTO;
import org.bs.board0.dto.MemberRegistDTO;

@Mapper
public interface MemberMapper {
    
    // Member Regist
    int memberRegist(MemberRegistDTO memberRegistDTO);

    // MemberRole Insert
    int memberRoleRegist(MemberRegistDTO MemberRegistDTO);

    // email로 member 한명의 정보 가져오기
    MemberReadDTO memberSelect(String email);
}
