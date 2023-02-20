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
        log.info("get Mapping hello!");
        List<BoardDTO> list = boardService.getBoardList();
        log.info("BoardList Test : " + list);
        model.addAttribute("list", list);
        return "list";
    }

    @GetMapping("/write")
    public String writePage() {
        log.info("Get write...");
        return "write";
    }

    @PostMapping("/write")
    public String insert(@Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        log.info("Post write...");
        if (bindingResult.hasErrors()) {
            log.error("write error!");
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
        }
        log.info("boardDTO : " + boardDTO);
        boardService.postArticle(boardDTO);

        return "redirect:/board/list";
    }


}
