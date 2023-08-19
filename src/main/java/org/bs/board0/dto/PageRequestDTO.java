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

    // type 배열로 반환 처리
    public String[] getTypes() {

        // 만약 type이 null이거나 비어있다면 null을 반환
        if (this.type == null || this.type.isEmpty()) {
            return null;
        }

        // type 문자열을 빈 문자열("")을 기준으로 나누어 배열로 반환
        return this.type.split("");
    }

    // link
    public String getLink() {

        if (link == null) {

            // 새로운 문자열 생성하기 위해 strBuiler 객체 생성
            StringBuilder strBuilder = new StringBuilder();

            // 페이지,사이즈 append -> 페이지,사이즈를 문자열 형태로 strBuilder에 추가
            strBuilder.append("page=" + this.page);
            strBuilder.append("&size=" + this.size);

            // 검색타입
            if (type != null && type.length() > 0) {
                
                // 검색타입을 strBuilder에 추가
                strBuilder.append("&type=" + this.type);
            }

            // 검색어
            if (keyword != null) {
                try {

                    // 키워드를 strBuilder에 추가. 
                    // 검색어는 URL 인코딩을 해야하기에 URLEncoder.encode()를 사용하여 인코딩된 문자열로 변환
                    strBuilder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));

                    // 예외 처리
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            // 모든조건 추가한 후 strBuilder의 내용을 문자열로 변환 후 link에 저장
            link = strBuilder.toString();
        }

        // link 반환
        return link;

    }

}
