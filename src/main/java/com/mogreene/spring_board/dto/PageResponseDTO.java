package com.mogreene.spring_board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    /* 시작페이지번호 */
    private int startPage;
    /* 마지막페이지번호 */
    private int endPage;

    /* 이전페이지 존재 여부 */
    private boolean prev;
    /* 다음페이지 존재 여부 */
    private boolean next;

    /* 나중에 게시판 뿐만 아닌 다른 정보들도 페이징 처리가 필요할 수 있음 */
    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.endPage = (int)(Math.ceil((double) this.page / 10)) * 10;
        this.startPage = endPage - 9;

        /* 총 게시물을 고려한 마지막 페이지 */
        int last = (int)(Math.ceil((total / (double)size)));

        this.endPage = Math.min(endPage, last);

        this.prev = this.startPage > 1;
        this.next = total > this.endPage * this.size;
    }
}
