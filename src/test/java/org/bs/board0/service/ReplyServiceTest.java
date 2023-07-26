package org.bs.board0.service;

import org.bs.board0.dto.PageRequestDTO;
import org.bs.board0.dto.PageResponseDTO;
import org.bs.board0.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {
    
    @Autowired(required = false)
    private ReplyService replyService;

    // 목록 리스트
    @Test
    public void replyListTest(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<ReplyDTO> list = replyService.replyList(2040L, pageRequestDTO);

        log.info(list);
    }

    // reply insert tno 값만 넣기 (원본댓글)
    @Test
    public void replyInsertTest(){

        ReplyDTO replyDTO = ReplyDTO.builder()
        .tno(2040L)
        .reply("ㅋㅋㅋ")
        .replyer("범수1234")
        .build();

        replyService.replyInsert(replyDTO);
        log.info("원본 댓글 등록 완료");

    }

    // replyChildInsert (대댓글) gno값 추가해서 넣어주기
    @Test
    public void replyChildInsertTest(){

        ReplyDTO replyDTO = ReplyDTO.builder()
        .tno(2040L)
        .gno(8L)
        .reply("8번댓글의 대댓글")
        .replyer("beomus")
        .build();

        replyService.replyInsert(replyDTO);
        log.info("대댓글 등록 완료");

    }

    // reply delete
    @Test
    public void replyDeleteTest(){

        replyService.replyDelete(9L);

        log.info("삭제 완료~!");
    }

    // reply update
    @Test
    public void replyUpdateTest(){

        ReplyDTO replyDTO = ReplyDTO.builder()
        .reply("수정")
        .rno(8L)
        .build();

        replyService.replyUpdate(replyDTO);

        log.info("수정 완료");
    }

}
