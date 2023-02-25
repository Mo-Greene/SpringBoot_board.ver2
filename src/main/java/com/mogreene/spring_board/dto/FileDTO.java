package com.mogreene.spring_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private Long fno;
    private String fileRealName;
    private String fileName;
    private String filePath;
}
