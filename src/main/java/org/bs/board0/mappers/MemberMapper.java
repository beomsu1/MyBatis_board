package org.bs.board0.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.bs.board0.dto.MemberRegistDTO;
import org.bs.board0.dto.MemberRoleRegistDTO;

@Mapper
public interface MemberMapper {
    
    int memberRegist(MemberRegistDTO memberRegistDTO);

    int memberRoleRegist(MemberRoleRegistDTO memberRoleRegistDTO);
}
