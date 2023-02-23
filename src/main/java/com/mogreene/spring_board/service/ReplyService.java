package com.mogreene.spring_board.service;

import com.mogreene.spring_board.dao.ReplyDAO;
import com.mogreene.spring_board.dto.ReplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyDAO replyDAO;

    // TODO: 2023/02/23 주석달기
    public void postReply(ReplyDTO replyDTO) {
        log.info("postReply do...");
        replyDAO.postReply(replyDTO);
    }

    public List<ReplyDTO> getReply(Long bno) {
        log.info("getReply do...");
        return replyDAO.getReplyList(bno);
    }
}
