package org.bs.board0.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> {

    private List<E> list;
    private int total;
    private boolean replyLast;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> list, int total, PageRequestDTO pageRequestDTO) {
        this.list = list;
        this.total = total;
        this.replyLast = pageRequestDTO.isReplyLast();

    }
}
