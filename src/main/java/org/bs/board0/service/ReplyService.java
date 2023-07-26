package org.bs.board0.service;

import org.bs.board0.dto.PageRequestDTO;
import org.bs.board0.dto.PageResponseDTO;
import org.bs.board0.dto.ReplyDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReplyService {
    
    // 댓글 목록
    PageResponseDTO<ReplyDTO> replyList (Long tno , PageRequestDTO pageRequestDTO);

    // replyInsert
    Long replyInsert(ReplyDTO replyDTO);

    // reply read
    ReplyDTO replyRead(Long rno);

    // reply delete
    int replyDelete(Long rno);

    // reply update
    int replyUpdate(ReplyDTO replyDTO);
}
