package com.mogreene.spring_board.dao;

import com.mogreene.spring_board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDAO {
    List<BoardDTO> getBoardList();
    void postArticle(BoardDTO boardDTO);
    BoardDTO getBoardView(Long bno);
    void deleteArticle(Long bno);
    void updateArticle(BoardDTO boardDTO);
    void viewCount(Long bno);
}
