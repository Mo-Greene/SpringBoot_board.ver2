package com.mogreene.spring_board.controller;

import com.mogreene.spring_board.dto.ReplyDTO;
import com.mogreene.spring_board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 댓글 컨트롤러
 */
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
    @PostMapping("/reply/write")
    public String writeReply(ReplyDTO replyDTO) {

        replyService.postReply(replyDTO);
        Long bno = replyDTO.getBno();
        return "redirect:/board/view/" + bno;
    }
}
