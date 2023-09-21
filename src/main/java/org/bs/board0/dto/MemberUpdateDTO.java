package org.bs.board0.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDTO {
    
    private String email;
    private String mpw;
    private String mname;
}
