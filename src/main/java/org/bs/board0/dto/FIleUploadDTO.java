package org.bs.board0.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileUploadDTO {

    private int imgno; // pk - 이미지 번호
    private String uuid; // uuid
    private String fname; // 파일 이름
    private boolean img; // 이미지 존재 유무

    // 이미지 파일 경로
    public String getLink(){

        // 이미지가 존재
        if(img){
            return "s_"+uuid+"_"+fname;
        }

        return "undefined.jpg";
    }
    
}
