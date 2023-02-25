package com.mogreene.spring_board.service;

import com.mogreene.spring_board.dao.FileDAO;
import com.mogreene.spring_board.dto.FileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileDAO fileDAO;

    /**
     * 파일 업로드
     * @param fileDTO
     * @return
     */
    public Long saveFile(FileDTO fileDTO) {
        log.info("FileService : " + fileDTO);
        fileDAO.saveFile(fileDTO);

        return fileDTO.getFno();
    }
}
