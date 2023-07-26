package org.bs.board0.mappers;

import java.util.List;

import org.bs.board0.dto.PageRequestDTO;
import org.bs.board0.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyMapperTest {
    
    @Autowired
    private ReplyMapper replyMapper;

    // replyList
    @Test
    public void replyListTest(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        Long tno = 2040L;
        
        List<ReplyDTO> list = replyMapper.replyList(tno, pageRequestDTO);

        log.info(list);
    }

    // 원본 댓글 생성 ----------------------------------------------

    // replyInsert
    @Test
    public void replyInsertTest(){

        ReplyDTO replyDTO = ReplyDTO.builder()
        .tno(2040L)
        .reply("댓글이에요")
        .replyer("범수123")
        .build();

        replyMapper.replyInsert(replyDTO);


        // gno값 rno값으로 변경

        Long rno = replyDTO.getRno();

        log.info("------------------------------");
        log.info("------------------------------");
        log.info("------------------------------");
        log.info(rno);
        
        replyMapper.replyUpdateGno(rno);

        log.info("댓글 생성 완료");

    }

    // 대댓글 추가
    @Test
    public void replyChildInsert(){

        ReplyDTO replyDTO = ReplyDTO.builder()
        .tno(2040L)
        .reply("대댓글")
        .replyer("범수123")
        .gno(5)
        .build();

        replyMapper.replyChildInsert(replyDTO);

        log.info("대댓글 생성 완료");

    }

    // 댓글 조회
    @Test
    public void replyRead(){

        log.info(replyMapper.replyRead(5L));
    }

    // 댓글 삭제
    @Test
    public void replyDelete(){

        replyMapper.replyDelete(7L);

        log.info("삭제되었습니다~!");
    }

    // 댓글 수정
    @Test
    public void replyUpdate(){

        ReplyDTO replyDTO = ReplyDTO.builder()
        .reply("변경할게요~!")
        .rno(6L)
        .build();

        replyMapper.replyUpdate(replyDTO);

        log.info("수정 완료~!");
    }

    


}
