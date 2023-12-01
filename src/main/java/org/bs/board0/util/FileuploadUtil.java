package org.bs.board0.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Component
@Log4j2
@RequiredArgsConstructor
public class FileuploadUtil {

    private final S3util s3util;

    @Value("${org.bs.upload.path}")
    private String uploadPath;

    @PostConstruct // 빈 초기화
    public void folder() {

        File folder = new File(uploadPath);

        if (folder.exists() == false) {
            folder.mkdir(); // 폴더 생성
        }

        uploadPath = folder.getAbsolutePath(); // 절대경로

        log.info("uploadPath: " + uploadPath);

    }

    public List<String> upload(List<MultipartFile> files) throws RuntimeException {

        if (files == null || files.size() == 0) {
            return null;
        }

        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : files) {

            // UUID_파일이름
            String saveName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            Path savePath = Paths.get(uploadPath, saveName);

            // 업로드 파일 경로
            List<Path> uploadFilePaths = new ArrayList<>();

            try {

                Files.copy(file.getInputStream(), savePath);

                // 원본 경로 저장
                uploadFilePaths.add(savePath);

                String contentType = file.getContentType();

                // 이미지 확인
                if (contentType != null && contentType.startsWith("image")) {

                    Path thumbnailPath = Paths.get(uploadPath, "s_" + saveName);

                    // 썸네일 생성
                    Thumbnails.of(savePath.toFile())
                            .size(400, 400)
                            .toFile(thumbnailPath.toFile());

                    // 썸네일 경로 저장
                    uploadFilePaths.add(thumbnailPath);
                }

                fileNames.add(saveName);

                // s3
                s3util.uploadFiles(uploadFilePaths);

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return fileNames;
    }

    // ResponseEntity - HTTP 응답을 생성하고 제어할 때 사용
    public ResponseEntity<Resource> getFile(String fileName) {

        // 원본 파일 리소스
        // File.separator = \
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        // 존재하지 않을 시
        if (!resource.exists()) {

            // 대체 파일 리소스
            resource = new FileSystemResource(uploadPath + File.separator + "default.jpeg");

        }

        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();

        try {
            // Files.probeContentType - 들어오는 리소스 MIME 확인
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    public void deleteFile(List<String> fileName) {

        if (fileName == null || fileName.size() == 0) {
            return;
        }

        List<Path> deleteFilePaths = new ArrayList<>();

        fileName.forEach(file -> {

            // 파일 확인
            String thumbnailFile = "s_" + file;
            Path thumbnailPath = Paths.get(uploadPath, thumbnailFile);
            Path filePath = Paths.get(uploadPath, file);

            // 삭제
            try {
                Files.deleteIfExists(filePath);
                Files.deleteIfExists(thumbnailPath);

                deleteFilePaths.add(filePath);
                deleteFilePaths.add(thumbnailPath);

                // s3
                s3util.deleteFile(deleteFilePaths);

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });

    }
}
