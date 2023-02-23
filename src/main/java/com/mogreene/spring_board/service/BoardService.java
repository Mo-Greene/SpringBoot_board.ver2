package com.mogreene.spring_board.service;

import com.mogreene.spring_board.dao.BoardDAO;
import com.mogreene.spring_board.dto.BoardDTO;
import com.mogreene.spring_board.dto.PageRequestDTO;
import com.mogreene.spring_board.dto.PageResponseDTO;
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
        log.info("getBoardList...");
        return boardDAO.getBoardList();
    }

    public PageResponseDTO<BoardDTO> getBoardListWithPaging(PageRequestDTO pageRequestDTO) {
        log.info("getBoardListWithPaging...");
        List<BoardDTO> list = boardDAO.getBoardListWithPaging(pageRequestDTO);
        int total = boardDAO.totalCount(pageRequestDTO);

        return PageResponseDTO.<BoardDTO>withAll()
                .dtoList(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    //TODO 주석달기
    public void postArticle(BoardDTO boardDTO) {
        boardDAO.postArticle(boardDTO);
    }

    //TODO 주석달기
    public BoardDTO getBoardView(Long bno) {
        log.info("getBoardView...");
        boardDAO.viewCount(bno);
        BoardDTO boardDTO = boardDAO.getBoardView(bno);
        log.info("boardDTO : " + boardDTO);
        return boardDTO;
    }

    // TODO: 2023/02/21 삭제 주석
    public void deleteArticle(Long bno) {
        log.info("deleteArticle...");
        boardDAO.deleteArticle(bno);
    }

    // TODO: 2023/02/21 게시글 수정 좀 더 디테일하게
    public void updateArticle(BoardDTO boardDTO) {
        log.info("updateArticle...");
        boardDAO.updateArticle(boardDTO);
    }
}
