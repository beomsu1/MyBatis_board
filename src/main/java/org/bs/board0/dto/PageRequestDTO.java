package org.bs.board0.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type; // 검색 종류
    private String keyword; // 검색어

    private String link; // 검색조건, 페이지, 사이즈 통합

    private boolean replyLast;

    // 페이지 설정
    public void setPage(int page) {

        if (page < 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    // 사이즈 설정
    public void setSize(int size) {

        if (size < 0 || size > 100) {
            this.size = 10;
        } else {
            this.size = size;
        }
    }

    // skip 메소드 생성
    public int getSkip() {

        return (this.page - 1) * this.size;
    }

    // end 메소드 생성
    public int getEnd() {

        return (this.page * this.size);
    }

    // 끝 위치 계산 메소드 생성
    public int getCountEnd() {

        int temp = (int) Math.ceil(this.page / 10.0) * (this.size * 10);

        // 중복된 페이지를 보여주지 않기 위해 +1
        return temp + 1;

    }

    // link
    public String getLink() {

        if (link == null) {
            // 문자열 합치기
            StringBuilder strBuilder = new StringBuilder();

            // 페이지,사이즈 append
            strBuilder.append("page=" + this.page);
            strBuilder.append("&size=" + this.size);

            // 검색타입
            if (type != null && type.length() > 0) {
                strBuilder.append("&type=" + this.type);
            }

            // 검색어
            if (keyword != null) {
                try {
                    strBuilder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            // toString으로 String전달
            link = strBuilder.toString();
        }

        return link;
        
    }

}
