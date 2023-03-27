package com.mogreene.spring_board.dao;

import com.mogreene.spring_board.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDAO {

    void saveFile(FileDTO fileDTO);
    FileDTO getFile(Long fno);
}
