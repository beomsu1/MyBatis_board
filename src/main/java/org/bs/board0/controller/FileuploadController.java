package org.bs.board0.controller;

import java.util.List;
import java.util.Map;

import org.bs.board0.util.FileuploadUtil;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/files/")
@RequiredArgsConstructor
public class FileuploadController {

    private final FileuploadUtil fileuploadUtil;

    // 파일 업로드
    @PostMapping("upload")
    public List<String> upload(List<MultipartFile> files) {

        log.info("POST | upload");

        List<String> fileNames = fileuploadUtil.upload(files);

        return fileNames;

    }

    // 파일 삭제
    @DeleteMapping("remove/{fname}")
    public String removeFile(@PathVariable("fname") List<String> fname) {

        log.info("delete | removeFile");

        fileuploadUtil.deleteFile(fname);

        return "삭제 완료";

    }
    

}
        

        
          

            
              

            
            
        

        
        

    
    
    
        
    

    
    

