package org.bs.board0.controller;

import java.util.Map;

import org.bs.board0.dto.PageRequestDTO;
import org.bs.board0.dto.PageResponseDTO;
import org.bs.board0.dto.ReplyDTO;
import org.bs.board0.service.ReplyService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/replies/")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    
    
    // replyList
    @GetMapping("{tno}/list")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("tno") Long tno , PageRequestDTO pageRequestDTO){
        
        log.info("Get : replyList...");

        return replyService.replyList(tno, pageRequestDTO);
    }

    // reply,childReply insert
    // @RequestBody: 보내는 데이터 JSON형식
    @PostMapping("{tno}/regist")
    public Map<String , Long> insertReply(@PathVariable("tno") Long tno , @RequestBody ReplyDTO replyDTO){

        log.info("post : insertReply");

        log.info(replyDTO);

        // replyDTO에 tno를 세팅
        replyDTO.setTno(tno);

        log.info("----------------------------------------------------------ttest");

        // 세팅된 replyDTO로 replyInsert 실행
        Long rno = replyService.replyInsert(replyDTO);

        return Map.of("result" , rno); 
    }

    // read reply
    @GetMapping("read/{rno}")
    public ReplyDTO readReply(@PathVariable("rno") Long rno){

        log.info("get : readReply...");

        return replyService.replyRead(rno);
    }

    // delete
    @DeleteMapping("delete/{rno}")
    public Map<String,Long> deleteReply(@PathVariable("rno") Long tno){

        log.info("delete : deleteReply");

        long result = replyService.replyDelete(tno);

        return Map.of("result" , result);
    }

    // update
    @PutMapping("modify/{rno}")
    public Map<String , Long> modifyReply(@RequestBody ReplyDTO replyDTO){

        log.info("put : modifyReply");

        long result = replyService.replyUpdate(replyDTO);

        return Map.of("result" , result);
    }

}
