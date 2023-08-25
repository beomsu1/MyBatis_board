package org.bs.board0.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bs.board0.dto.FileUploadDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j2
@RestController
@RequestMapping("/api/files/")
@RequiredArgsConstructor
public class FileuploadController {
    

    // uploadPath 변수에 "org.bs.upload.path"라는 프로퍼티의 값을 주입하는 역할
    @Value("${org.bs.upload.path}")
    private String uploadPath;

    // 파일 업로드
    @PostMapping("upload")
    public List<FileUploadDTO> upload(MultipartFile[] files){

        log.info("POST | upload");

        // 파일이 존재하지 않으면 return
        if(files == null || files.length < 0){
            return null;
        }

        // 배열로 list 받기
        List<FileUploadDTO> fileList = new ArrayList<>();

        // for each 사용
        // files라는 배열에서 MultipartFile라는 객체들을 순서대로 file이라는 변수에 담기
        for (MultipartFile file : files) {
            
            // 초기값 설정
            FileUploadDTO result = null;

            // 실제 파일명
            String fname = file.getOriginalFilename();

            // UUID 생성
            String uuidStr = UUID.randomUUID().toString();

            // UUID를 붙인 파일명 생성
            String SaveFname = uuidStr+"_"+fname;

            // 실제 파일 저장
            // uploadPath에 UUID 붙인 파일을 생성해서 저장
            File saveFile = new File(uploadPath, SaveFname);

            // 예외처리
            try {

                // FileCopyUtils를 사용해서 InputStream으로 받고 OutputStream으로 내보내기
                // GetBytes() -> 파일을 바이트 배열로 받음 
                FileCopyUtils.copy(file.getBytes(), saveFile);

                // result에 값 넣어서 객체 만들기
                result = FileUploadDTO.builder()
                .uuid(uuidStr)
                // 실제 파일명을 넣어줘야함
                .fname(fname)
                .build();

                // 파일 확장자 체크
                // Files.probeContentType(saveFile.toPath()) -> 
                // Java NIO(새로운 입출력)의 Files 클래스를 사용하여 파일의 MIME 타입을 검사하는 코드
                String mimeType = Files.probeContentType(saveFile.toPath());
                
                // mimeType == image 체크
                if(mimeType.startsWith("image")){

                    // 썸네일 생성, 이름에 s_ 사용
                    File thumbFile = new File(uploadPath, "s_"+SaveFname);
                    
                    // 썸네일 생성 시 (원본파일, 썸네일파일, width, height)
                    Thumbnailator.createThumbnail(saveFile, thumbFile, 80, 80);

                    // getLink 사용하기 위해 true로 변경
                    result.setImg(true);

                }

                // 리스트에 result 담기
                fileList.add(result);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return fileList;
    }
    

    // 파일 삭제
    @DeleteMapping("remove/{fname}")
    public Map<String, String> removeFile(@PathVariable("fname") String fname){

        log.info("delete | removeFile");

        // 받은 파일로 객체 생성
        File originFile = new File(uploadPath, fname);

        try {
            
            // mimeType 확장자 체크
            String mimeType = Files.probeContentType(originFile.toPath());
            
            // mimeType == imamge
            if(mimeType.startsWith("image")){

                // 썸네일 파일이름 생성
                File thumbFile = new File(uploadPath, "s_"+fname);

                // 만든 썸네일 삭제
                thumbFile.delete();
            }

            // 원본 파일 삭제
            originFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // JSON 데이터로 반환
        return Map.of("result", "success");
        
    }
}
