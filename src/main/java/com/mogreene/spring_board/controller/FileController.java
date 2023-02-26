package com.mogreene.spring_board.controller;

import com.mogreene.spring_board.dto.FileDTO;
import com.mogreene.spring_board.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 파일컨트롤러
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    /**
     *
     * @param fno
     * @return
     * @throws IOException
     */
    // TODO: 2023/02/25 컨트롤러랑 서비스로 분리해야됨
    @GetMapping("/download/{fno}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fno") Long fno) throws IOException {
        FileDTO fileDTO = fileService.getFile(fno);
        Path path = Paths.get(fileDTO.getFilePath());

        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileDTO.getFileRealName() + "\"")
                .body(resource);
    }
}
