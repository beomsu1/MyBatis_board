package org.bs.board0.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

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

        int temp = (int)(Math.ceil(this.page / 10.0)) * this.size * 10;

        // 중복된 페이지를 보여주지 않기 위해 +1
        return temp + 1;

    }

}
