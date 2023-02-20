package com.mogreene.spring_board.controller;

import com.mogreene.spring_board.dto.BoardDTO;
import com.mogreene.spring_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * Test 조회
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        log.info("Get List...");
        List<BoardDTO> list = boardService.getBoardList();
        log.info("BoardList Test : " + list);
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
        if (bindingResult.hasErrors()) {
            log.error("write error!");
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());

            return "redirect:/board/write";
        }
        log.info("boardDTO : " + boardDTO);
        boardService.postArticle(boardDTO);

        return "redirect:/board/list";
    }


}
