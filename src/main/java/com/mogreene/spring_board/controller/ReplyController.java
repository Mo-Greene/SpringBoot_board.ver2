package com.mogreene.spring_board.controller;

import com.mogreene.spring_board.dto.ReplyDTO;
import com.mogreene.spring_board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 댓글 등록
     * @param replyDTO
     * @return
     */
    // TODO: 2023/02/23 비동기 처리 필요
    @PostMapping("/reply/write")
    public String writeReply(ReplyDTO replyDTO) {
        log.info("writeReply...");
        log.info("replyDTO : " + replyDTO);
        replyService.postReply(replyDTO);
        Long bno = replyDTO.getBno();
        return "redirect:/board/view/" + bno;
    }
}
