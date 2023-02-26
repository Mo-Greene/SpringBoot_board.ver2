package com.mogreene.spring_board.service;

import com.mogreene.spring_board.dao.BoardDAO;
import com.mogreene.spring_board.dto.BoardDTO;
import com.mogreene.spring_board.dto.PageRequestDTO;
import com.mogreene.spring_board.dto.PageResponseDTO;
import com.mogreene.spring_board.util.MD5Generator;
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
     * 게시글 페이지네이션 조회 + 제목 80자 이상 제한
     * @param pageRequestDTO
     * @return
     */
    public PageResponseDTO<BoardDTO> getBoardListWithPaging(PageRequestDTO pageRequestDTO) {
        log.info("getBoardListWithPaging...");
        List<BoardDTO> list = boardDAO.getBoardListWithPaging(pageRequestDTO);
        skipTitle(list);
        int total = boardDAO.totalCount(pageRequestDTO);

        return PageResponseDTO.<BoardDTO>withAll()
                .dtoList(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    /**
     * getBoardListWithPaging 메서드 추출
     * @param list
     */
    // TODO: 2023/02/25 글자수 바이트 고려한 고민
    private void skipTitle(List<BoardDTO> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().length() > 80) {
                String title = list.get(i).getTitle().substring(0, 80) + "...";
                list.get(i).setTitle(title);
            }
        }
    }

    /**
     * 게시글 등록 및 비밀번호
     * @param boardDTO
     * @throws Exception
     */
    public void postArticle(BoardDTO boardDTO) throws Exception {
        log.info("postArticle...");

        String password = new MD5Generator(boardDTO.getPassword()).toString();
        boardDTO.setPassword(password);

        boardDAO.postArticle(boardDTO);
    }

    /**
     * 게시글 상세조회
     * @param bno
     * @return boardDTO
     */
    public BoardDTO getBoardView(Long bno) {
        log.info("getBoardView...");

        boardDAO.viewCount(bno);
        BoardDTO boardDTO = boardDAO.getBoardView(bno);
        return boardDTO;
    }

    /**
     * 게시글 삭제
     * @param bno
     */
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
//        if (!passwordEncoder.matches(boardDTO.getPassword(), password)) {
//            throw new Exception("비밀번호가 같지 않습니다.");
//        }
        boardDAO.updateArticle(boardDTO);
    }
}
