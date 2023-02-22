package com.mogreene.spring_board.controller;

import com.mogreene.spring_board.dto.BoardDTO;
import com.mogreene.spring_board.service.BoardService;
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

    /**
     * 전체 조회
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        log.info("Get List...");
        List<BoardDTO> list = boardService.getBoardList();
        model.addAttribute("list", list);
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
                         RedirectAttributes redirectAttributes) {
        log.info("Post write...");
        //todo binding error thymeleaf 로 뽑아내기
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
     * @param bno
     * @param boardDTO
     * @return
     */
    // TODO: 2023/02/21 put, delete 적용 해야 ajax 로 보내는걸로 하자
    @PostMapping("/modify/{bno}")
    public String updateArticle(@PathVariable("bno") Long bno, BoardDTO boardDTO) {
        log.info("bno : " + bno);
        log.info("modifyArticle...");
        boardService.updateArticle(bno, boardDTO);
        return "redirect:/board/list";
    }
}
