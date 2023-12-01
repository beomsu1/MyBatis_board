package org.bs.board0.util;

import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
@RequiredArgsConstructor
public class S3util {

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    private final S3Client s3Client;

    // 업로드
    public void uploadFiles(List<Path> filePath){

        if(filePath == null || filePath.size() == 0){
            return;
        }

        for (Path path : filePath) {

            // s3 업로드 필요한 객체 생성
            PutObjectRequest request = PutObjectRequest.builder()
            .bucket(bucket) // 버킷 이름
            .key(path.toFile().getName()) // 식별 키
            .build();

            s3Client.putObject(request, path);
            
        }
    }

    // 삭제
    public void deleteFile(List<Path> filePath){

        if(filePath == null || filePath.size() == 0){
            return;
        }

        for (Path path : filePath) {

            DeleteObjectRequest request = DeleteObjectRequest.builder()
            .bucket(bucket)
            .key(path.toFile().getName())
            .build();

            s3Client.deleteObject(request);
            
        }

    }
    
}
