package com.mogreene.spring_board.dto;

import lombok.Data;

@Data
public class PageDTO {

    /* 1.현재 페이지 번호 */
    private int viewPage;

    /* 2.페이지 당 출력 데이터 */
    private int size;

    /* 3.페이지 블럭 사이즈 */
    private int blockSize;

    public PageDTO() {
        this.viewPage = 1;
        this.size = 10;
        this.blockSize = 10;
    }

    public int getOffset() {
        return (viewPage - 1) * size;
    }
}
