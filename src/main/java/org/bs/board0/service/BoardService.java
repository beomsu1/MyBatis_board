package org.bs.board0.service;


import org.bs.board0.dto.BoardDTO;
import org.bs.board0.dto.PageRequestDTO;
import org.bs.board0.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {

        // list
        PageResponseDTO<BoardDTO> boardList(PageRequestDTO pageRequestDTO);

        // insert
        void boardInsert(BoardDTO boardDTO);
    
        // read
        BoardDTO boardRead(Long tno);
    
        // update
        void boardUpdate(BoardDTO boardDTO);
    
        // delete
        int boardDelete(Long tno);
    
}
