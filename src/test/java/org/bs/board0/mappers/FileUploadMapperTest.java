package org.bs.board0.mappers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.bs.board0.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class FileUploadMapperTest {

    @Autowired(required = false)
    private FileUploadMapper fileUploadMapper;

    @Autowired(required = false)
    private BoardMapper boardMapper;

    // insert
    @Test
    @Transactional
    @Commit
    public void testInsertImage() {

        // boardDTO 객체 생성
        BoardDTO boardDTO = BoardDTO.builder()
                .title("test Image")
                .content("test Image")
                .writer("beomsu")
                .fnames(List.of(UUID.randomUUID() + "_beomsu1.jpg", UUID.randomUUID() + "_beomsu2.jpg"))
                .build();

        // 생성한 boardDTO객체에서 파일이름 List로 가져오기
        List<String> fnames = boardDTO.getFnames();

        // 게시물 등록
        int count = boardMapper.boardInsert(boardDTO);
        log.info("count: " + count);

        // 게시물 등록이 되었다면 실행
        // fname != null , fname이 존재 시
        if (count > 0 && boardDTO.getFnames() != null && !boardDTO.getFnames().isEmpty()) {

            // tno 가져오기
            Long tno = boardDTO.getTno();
            log.info(tno);

            // 새로운 AtomicInteger 객체를 생성하고,
            // 이 객체를 통해 원자적으로 정수 값을 증가시키거나 관리할 수 있도록 함
            AtomicInteger index = new AtomicInteger();

            // 등록된 파일에서 값들을 추출 fnames
            // str은 fnames 리스트의 각 요소
            List<Map<String, String>> list = fnames.stream().map(str -> {

                // uuid 0~36까지 자르기
                String uuid = str.substring(0, 36);

                // 파일명 가져오기 ~37 fname에 _가 있어서 37까지 잘라줘야 제대로 fname을 출력
                String fname = str.substring(37);

                // 맵들을 리스트로 묶기
                return Map.of("uuid", uuid, "fname", fname, "tno", "" + tno, "ord", "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            log.info("------------");
            log.info(list);

            // 파일 등록
            fileUploadMapper.insertImages(list);

        }
    }


    // delete
    @Test
    @Transactional
    @Commit
    public void testDeleteImage(){

        fileUploadMapper.deleteImages(1310705L);
    }

}
