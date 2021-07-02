package org.zerock.j07.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.j07.board.dto.BoardListRequestDTO;
import org.zerock.j07.board.service.BoardService;
import org.zerock.j07.common.dto.ListRequestDTO;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(BoardListRequestDTO listRequestDTO) {
        return ResponseEntity.ok().body(boardService.getList(listRequestDTO));
    }

}
