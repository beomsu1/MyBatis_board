package org.bs.board0.dto;

import java.util.List;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberReadDTO {

    private String email;
    private String mpw;
    private String mname;
    private List<String> rolenames;
    
}
