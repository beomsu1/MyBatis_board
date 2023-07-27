package org.bs.board0.service;

import java.util.List;

import org.apache.ibatis.executor.ExecutorException;
import org.bs.board0.dto.PageRequestDTO;
import org.bs.board0.dto.PageResponseDTO;
import org.bs.board0.dto.ReplyDTO;
import org.bs.board0.mappers.ReplyMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;

    // 목록 리스트
    @Override
    public PageResponseDTO<ReplyDTO> replyList(Long tno, PageRequestDTO pageRequestDTO) {

        // 댓글 목록을 30개씩 불러오게 설정
        pageRequestDTO.setSize(30);

        // total
        int total = replyMapper.replyTotal(tno);

        // page번호
        int pageNum = pageRequestDTO.getPage();

        // 끝페이지 계산
        if (!pageRequestDTO.isReplyLast()) {
            
            // 페이지 넘버에 넣어주기
            pageNum = (int)(Math.ceil(total / (double) pageRequestDTO.getSize()));

            // 페이지번호가 0보다 작거나 같으면 1로 설정
            pageNum = pageNum <= 0 ? 1 : pageNum;
        }
        // 끝페이지 번호로 설정
        pageRequestDTO.setPage(pageNum);

        // list 가져오기
        List<ReplyDTO> list = replyMapper.replyList(tno, pageRequestDTO);

        return PageResponseDTO.<ReplyDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

    }

    // 댓글 추가
    @Override
    public Long replyInsert(ReplyDTO replyDTO) {
        Long result = null;

        Long gno = replyDTO.getGno();

        // 댓글
        if (gno == 0) {

        log.info("Reply Service----------------------------" + replyMapper.replyInsert(replyDTO));
            // 값이 들어왔나 확인 하기위해 int count라는 변수에 담자
            int count = replyMapper.replyInsert(replyDTO);

            // 예외처리
            if (count != 1) {
                throw new RuntimeException("Reply insert Error");
            }

            // rno 값으로 gno업데이트
            Long rno = replyDTO.getRno();
            replyMapper.replyUpdateGno(rno);

            result = rno;

        } else {

            // 대댓글
            int count = replyMapper.replyChildInsert(replyDTO);

            // 예외처리
            if (count != 1) {
                throw new ExecutorException("Reply insert Error");
            }

            result = replyDTO.getRno();
        }


        return result;
    }

    // 댓글 조회
    @Override
    public ReplyDTO replyRead(Long rno) {

        return replyMapper.replyRead(rno);
    }

    // 댓글 삭제
    @Override
    public int replyDelete(Long rno) {

        return replyMapper.replyDelete(rno);
    }

    // 댓글 수정
    @Override
    public int replyUpdate(ReplyDTO replyDTO) {

        return replyMapper.replyUpdate(replyDTO);
    }

}
