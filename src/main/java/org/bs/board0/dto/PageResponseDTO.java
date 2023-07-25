package org.bs.board0.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class PageResponseDTO<E> {

    private List<E> list;
    private int total;

    public PageResponseDTO(List<E> list, int total) {
        this.list = list;
        this.total = total;
    }
}
