package org.bs.board0.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileUploadMapper {

    // insert
    int insertImages(List<Map<String,String>> imageList);

    // delete
    int deleteImages(int tno);
    
}
