package org.bs.board0.mappers;

import java.util.List;

import org.bs.board0.dto.BoardDTO;
import org.bs.board0.dto.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardMapperTest {

    @Autowired(required = false)
    private BoardMapper boardMapper;

    // List Test
    @Test
    public void boardListTest(){
        
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        List<BoardDTO> list = boardMapper.boardList(pageRequestDTO);

        log.info(list);
    }
    
    // insert Test
    @Test
    public void boardInsertTest(){

        BoardDTO boardDTO = BoardDTO.builder()
        .title("안녕")
        .content("ㅎㅇ")
        .writer("범수")
        .build();

        boardMapper.boardInsert(boardDTO);

        log.info("insert OK!!");
    }

    // read Test
    @Test
    public void boardReadTest(){

        BoardDTO boardDTO = boardMapper.boardRead(2048L);

        log.info(boardDTO);

    }

    // update Test
    @Test
    public void boardUpdateTest(){

        BoardDTO boardDTO = BoardDTO.builder()
        .title("title1")
        .content("content1")
        .writer("writer1")
        .tno(2048L)
        .build();

        boardMapper.boardUpdate(boardDTO);

        log.info("수정 완료");
    }

    // delete test
    @Test
    public void boardDeleteTest(){

        boardMapper.boardDelete(2048L);

        log.info("삭제 완료");
    }

    // total
    @Test
    public void totalTest(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        boardMapper.total(pageRequestDTO);

    }
}
