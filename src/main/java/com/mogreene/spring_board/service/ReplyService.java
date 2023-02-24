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

    /**
     * 댓글 등록
     * @param replyDTO
     */
    public void postReply(ReplyDTO replyDTO) {
        log.info("postReply do...");
        replyDAO.postReply(replyDTO);
    }

    /**
     * 댓글 조회
     * @param bno
     * @return
     */
    public List<ReplyDTO> getReply(Long bno) {
        log.info("getReply do...");
        return replyDAO.getReplyList(bno);
    }
}
