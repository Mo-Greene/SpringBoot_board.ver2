package com.mogreene.spring_board.controller;

import com.mogreene.spring_board.dto.BoardDTO;
import com.mogreene.spring_board.dto.PageRequestDTO;
import com.mogreene.spring_board.dto.ReplyDTO;
import com.mogreene.spring_board.service.BoardService;
import com.mogreene.spring_board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * 게시판 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;

    /**
     * 게시글 페이지 네이션
     * @param pageRequestDTO
     * @param bindingResult
     * @param model
     * @return list.html
     */
    @GetMapping("/list")
    public String listWithPaging(@Valid PageRequestDTO pageRequestDTO,
                                 BindingResult bindingResult,
                                 Model model) {
        log.info("Get ListWithPaging...");

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("list", boardService.getBoardListWithPaging(pageRequestDTO));

        return "list";
    }

    /**
     * 게시글 등록화면
     * @return
     */
    @GetMapping("/write")
    public String writePage() {
        log.info("Get write...");
        return "write";
    }

    /**
     * 게시글 등록
     * @param boardDTO
     * @param bindingResult
     * @param redirectAttributes
     * @return write.html
     */
    @PostMapping("/write")
    public String insert(@Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) throws Exception {
        log.info("Post write...");
        // TODO: 2023/02/22 유효성 검사 프론트부분 아직 안됐음
        if (bindingResult.hasErrors()) {
            log.error("write error!");
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());

            return "redirect:/board/write";
        }
        boardService.postArticle(boardDTO);

        return "redirect:/board/list";
    }

    /**
     * 특정 게시글 조회
     * @param bno
     * @param model
     * @return view.html
     */
    @GetMapping("/view/{bno}")
    public String getBoardView(@PathVariable("bno") Long bno, Model model) {
        log.info("getBoardView...");
        BoardDTO boardDTO = boardService.getBoardView(bno);
        List<ReplyDTO> replyList = replyService.getReply(bno);
        boardDTO.setReplyList(replyList);
        model.addAttribute("dto", boardDTO);
        return "view";
    }

    /**
     * 게시글 삭제
     * @param bno
     * @return
     */
    @DeleteMapping("/delete/{bno}")
    public String deleteArticle(@PathVariable("bno") Long bno) {
        log.info("deleteArticle...");
        boardService.deleteArticle(bno);
        return "redirect:/board/list";
    }

    /**
     * 수정 페이지 Get
     * @param bno
     * @param model
     * @return
     */
    // TODO: 2023/02/22 게시글 수정 조회시에도 조회수가 올라감! 
    @GetMapping("/modify/{bno}")
    public String getModify(@PathVariable("bno") Long bno, Model model) {
        log.info("getModify...");
        BoardDTO boardDTO = boardService.getBoardView(bno);
        model.addAttribute("dto", boardDTO);
        return "modify";
    }

    /**
     * 게시글 수정
     * @param boardDTO
     * @return
     */
    // TODO: 2023/02/23 수정버튼 클릭시 조회수 증가 수정필요
    // TODO: 2023/02/23 예외처리 필요
    @PostMapping("/modify/{bno}")
    public String updateArticle(BoardDTO boardDTO) throws Exception {
        log.info("updateArticle...");
        try {
            boardService.updateArticle(boardDTO);
        } catch (Exception e) {
            throw new Exception("비밀번호가 같지 않습니다.");
        }
        return "redirect:/board/list";
    }
}
