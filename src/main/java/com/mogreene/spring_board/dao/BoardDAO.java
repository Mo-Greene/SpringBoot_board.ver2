package com.mogreene.spring_board.dao;

import com.mogreene.spring_board.dto.BoardDTO;
import com.mogreene.spring_board.dto.PageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDAO {
    int totalCount();
    List<BoardDTO> getBoardList();
    List<BoardDTO> getBoardListWithPaging(PageDTO pageDTO);
    void postArticle(BoardDTO boardDTO);
    BoardDTO getBoardView(Long bno);
    void deleteArticle(Long bno);
    void updateArticle(BoardDTO boardDTO);
    void viewCount(Long bno);
}
