package com.mogreene.spring_board.service;

import com.mogreene.spring_board.dao.BoardDAO;
import com.mogreene.spring_board.dto.BoardDTO;
import com.mogreene.spring_board.dto.PageRequestDTO;
import com.mogreene.spring_board.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDAO boardDAO;
    private final PasswordEncoder passwordEncoder;

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

    /**
     * 게시글 등록 및 비밀번호 복호화
     * @param boardDTO
     * @throws Exception
     */
    public void postArticle(BoardDTO boardDTO) throws Exception {
        log.info("postArticle...");

        if (!boardDTO.getPassword().equals(boardDTO.getPasswordCheck())) {
            throw new Exception("비밀번호가 같지 않습니다.");
        } else {
            String password = passwordEncoder.encode(boardDTO.getPassword());
            boardDTO.setPassword(password);
            boardDAO.postArticle(boardDTO);
        }
    }

    //TODO 주석달기
    public BoardDTO getBoardView(Long bno) {
        log.info("getBoardView...");

        boardDAO.viewCount(bno);
        BoardDTO boardDTO = boardDAO.getBoardView(bno);
        return boardDTO;
    }

    // TODO: 2023/02/21 삭제 주석
    public void deleteArticle(Long bno) {
        log.info("deleteArticle...");

        boardDAO.deleteArticle(bno);
    }

    /**
     * 게시글 수정 비밀번호 확인 로직 추가
     * @param boardDTO
     */
    public void updateArticle(BoardDTO boardDTO) throws Exception {
        log.info("updateArticle...");

        String password = boardDAO.dbPassword(boardDTO);
        if (!passwordEncoder.matches(boardDTO.getPassword(), password)) {
            throw new Exception("비밀번호가 같지 않습니다.");
        }
        boardDAO.updateArticle(boardDTO);
    }
}
