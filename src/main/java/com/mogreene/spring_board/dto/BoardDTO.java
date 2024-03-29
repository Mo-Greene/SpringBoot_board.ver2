package com.mogreene.spring_board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long bno;

    @NotBlank(message = "제목을 적어주세요")
    @Size(min = 4, max = 100, message = "제목은 4글자 이상 100글자 이하으로 작성가능")
    private String title;

    @NotBlank(message = "내용을 적어주세요")
    @Size(min = 4, max = 2000, message = "내용은 4글자 이상 2000자 이하로 적어주세요")
    private String content;

    @NotBlank(message = "작성자를 적어주세요")
    @Size(min = 3, max = 5, message = "이름은 3~5글자 까지 가능합니다.")
    private String writer;


    @NotBlank(message = "비밀번호를 적어주세요")
    @Pattern(regexp = "[a-zA-Z1-9]{4,16}", message = "비밀번호는 영문, 숫자 포함 4~16자 이내로 작성해주세요.")
    private String password;
    private String passwordCheck;
    private String regDate;
    private String modDate;
    private int view;
    private Long cno;               //category fk
    private String category;
    private List<ReplyDTO> replyList;
    private Long fno;               //file fk
}
