package org.bs.board0.controller;

import org.bs.board0.dto.BoardDTO;
import org.bs.board0.dto.PageRequestDTO;
import org.bs.board0.dto.PageResponseDTO;
import org.bs.board0.service.BoardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/")
@RequiredArgsConstructor
@EnableMethodSecurity
public class BoardController {

    private final BoardService boardService;

    // list
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void getList(PageRequestDTO pageRequestDTO, Model model) {

        log.info("get List...");

        PageResponseDTO<BoardDTO> boardList = boardService.boardList(pageRequestDTO);

        model.addAttribute("boardList", boardList);
    }

    // get Regist
    @GetMapping("regist")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void getRegist() {

        log.info("get Regist...");

    }

    // post Regist
    @PostMapping("regist")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String postRegist(BoardDTO boardDTO) {

        log.info(boardDTO + "boardDTO");

        boardService.boardInsert(boardDTO);

        log.info("post Regist...");

        return "redirect:/board/list";
    }

    // get Read
    @GetMapping("read/{tno}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getRead(@PathVariable("tno") Long tno, Model model) {

        log.info("getRead...");

        BoardDTO boardDTO = boardService.boardRead(tno);

        model.addAttribute("boardRead", boardDTO);

        return "/board/read";
    }

    // get Modify
    @GetMapping("modify/{tno}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getModify(@PathVariable("tno") Long tno, Model model) {

        log.info("getModify...");

        BoardDTO boardDTO = boardService.boardRead(tno);

        model.addAttribute("boardModify", boardDTO);

        return "/board/modify";
    }

    // post Modify
    @PostMapping("modify")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String postModify(BoardDTO boardDTO) {

        log.info("postModify...");

        boardService.boardUpdate(boardDTO);

        return "redirect:/board/read/" + boardDTO.getTno();
    }

    // post Delete
    @PostMapping("delete/{tno}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String postDelete(@PathVariable("tno") Long tno) {

        log.info("postDelete...");

        boardService.boardDelete(tno);

        return "redirect:/board/list";
    }

}
