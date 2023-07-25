package org.bs.board0.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDTO {

    private Long tno;

    private String title;

    private String content;

    private String writer;

    private LocalDate regDate;

    private LocalDate updateDate;
    
}
