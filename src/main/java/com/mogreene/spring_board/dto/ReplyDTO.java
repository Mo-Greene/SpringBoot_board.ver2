package com.mogreene.spring_board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    private Long rno;
    private String replyContent;
    private String regDate;
    private Long bno;   //fk
}
