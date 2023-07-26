package org.bs.board0.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bs.board0.dto.PageRequestDTO;
import org.bs.board0.dto.ReplyDTO;

@Mapper
public interface ReplyMapper {

    // 댓글 목록 (게시물을 찾아야함 tno 파람으로 찾기)
    // @Param = 해당 메소드를 호출할 떄 해당 파라미터로 전달한 값이 자동으로 변수에 저장
    List<ReplyDTO> replyList (@Param("tno") Long tno , @Param("pr") PageRequestDTO pageRequestDTO);

    ///////// 댓글 추가 gno = 0이여야 댓글 //////////

    // replyInsert
    int replyInsert(ReplyDTO replyDTO);

    // rno를 받아서 gno값 세팅
    int replyUpdateGno(Long rno);

    ///////////////////////////////////////////////

    // 대댓글 추가
    int replyChildInsert(ReplyDTO replyDTO);

    // reply read
    ReplyDTO replyRead(Long rno);

    // reply delete
    int replyDelete(Long rno);

    // reply update
    int replyUpdate(ReplyDTO replyDTO);

    // reply total
    int replyTotal(Long rno);

}
