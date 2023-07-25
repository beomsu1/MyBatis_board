package org.bs.board0.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.bs.board0.dto.BoardDTO;
import org.bs.board0.dto.PageRequestDTO;

@Mapper
public interface BoardMapper {
    
    // list
    List<BoardDTO> boardList(PageRequestDTO pageRequestDTO);

    // insert
    int boardInsert(BoardDTO boardDTO);

    // read
    BoardDTO boardRead(Long tno);

    // update
    int boardUpdate(BoardDTO boardDTO);

    // delete
    int boardDelete(Long tno);
    
    // total
    int total(PageRequestDTO pageRequestDTO);

    

}
