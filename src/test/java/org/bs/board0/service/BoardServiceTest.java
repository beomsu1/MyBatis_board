package org.bs.board0.service;


import org.bs.board0.dto.BoardDTO;
import org.bs.board0.dto.PageRequestDTO;
import org.bs.board0.dto.PageResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    // list Test
    @Test
    public void boardListTest(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<BoardDTO> list= boardService.boardList(pageRequestDTO);

        log.info(list);
    }

    // insert Test
    @Test
    @Transactional
    @Commit
    public void boardInsertTest(){

        BoardDTO boardDTO = BoardDTO.builder()
        .title("title12")
        .content("content12")
        .writer("beomsu12")
        .build();

        boardService.boardInsert(boardDTO);

        log.info ("insert 성공");
    }

    // read
    @Test
    public void boardReadTest(){

        Long tno = 2049L;

        log.info(boardService.boardRead(tno));
    }

    // update
    @Test
    @Commit
    @Transactional
    public void boardUpdateTest(){

        BoardDTO boardDTO = BoardDTO.builder()
        .title("beomsu1")
        .content("content1")
        .writer("beomsu123")
        .tno(2049L)
        .build();

        boardService.boardUpdate(boardDTO);

        log.info("수정 완료!");
    }

    // delete
    @Test
    public void boardDeleteTest(){

        Long tno = 2049L;

        boardService.boardDelete(tno);

        log.info("삭제 완료!");

    }
    
}
