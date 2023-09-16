package org.bs.board0.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegistDTO {
    
    private String email;
    private String mpw;
    private String mname;
    private String rolename;
    
}
