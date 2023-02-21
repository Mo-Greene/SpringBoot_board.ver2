package com.mogreene.spring_board.service;

import com.mogreene.spring_board.dao.BoardDAO;
import com.mogreene.spring_board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDAO boardDAO;

    /**
     * 전체 게시글 조회
     * @return List<BoardDTO>
     */
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> list = boardDAO.getBoardList();
        //TODO modDate "-" 처리해야됨
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModDate() == null) {
                list.get(i).setModDate("-");
            }
        }
        return boardDAO.getBoardList();
    }

    //TODO 주석달기
    public void postArticle(BoardDTO boardDTO) {
        boardDAO.postArticle(boardDTO);
    }

    //TODO 주석달기
    public BoardDTO getBoardView(Long bno) {
        log.info("getBoardView...");
        BoardDTO boardDTO = boardDAO.getBoardView(bno);
        boardDAO.viewCount(bno);
        log.info("boardDTO : " + boardDTO);
        return boardDTO;
    }

    // TODO: 2023/02/21 삭제 주석
    public void deleteArticle(Long bno) {
        log.info("deleteArticle...");
        boardDAO.deleteArticle(bno);
    }

    // TODO: 2023/02/21 게시글 수정 좀 더 디테일하게
    public void updateArticle(Long bno, BoardDTO boardDTO) {
        log.info("updateArticle...");
        boardDAO.updateArticle(bno, boardDTO);
    }
}
