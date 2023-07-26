package org.bs.board0.service;

import java.util.List;

import org.bs.board0.dto.BoardDTO;
import org.bs.board0.dto.PageRequestDTO;
import org.bs.board0.dto.PageResponseDTO;
import org.bs.board0.mappers.BoardMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    // list
    @Override
    public PageResponseDTO<BoardDTO> boardList(PageRequestDTO pageRequestDTO) {

        int total = boardMapper.total(pageRequestDTO);

        List<BoardDTO> list = boardMapper.boardList(pageRequestDTO);

        // PageResponseDTO 객체 생성
        return PageResponseDTO.<BoardDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    // insert
    @Override
    public int boardInsert(BoardDTO boardDTO) {

        return boardMapper.boardInsert(boardDTO);
    }

    // read
    @Override
    public BoardDTO boardRead(Long tno) {

        return boardMapper.boardRead(tno);
    }

    // update
    @Override
    public int boardUpdate(BoardDTO boardDTO) {

        return boardMapper.boardUpdate(boardDTO);
    }

    // delete
    @Override
    public int boardDelete(Long tno) {

        return boardMapper.boardDelete(tno);
    }

}
