package org.bs.board0.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyDTO {

    // 댓글

    private Long rno; // pk

    private Long tno; // fk - 게시물 번호

    private String reply; // 내용

    private String replyer; // 작성자

    private String replyDate; // 작성 시간

    // 대댓글

    @Builder.Default
    private Long gno = 0L; // 대댓글처리 번호 0 default설정 - gno가 1일 때 대댓글

    private int step; // 대댓글 여부 확인

    // true로 나오면 삭제처리
    private boolean status; // 댓글 삭제여부

}
