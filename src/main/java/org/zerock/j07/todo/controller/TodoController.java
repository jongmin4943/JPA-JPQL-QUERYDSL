package org.zerock.j07.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.ListRequestDTO;
import org.zerock.j07.todo.dto.TodoDTO;
import org.zerock.j07.todo.service.TodoService;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<TodoDTO>> list(ListRequestDTO listRequestDTO) {
        return  ResponseEntity.ok().body( todoService.list(listRequestDTO));
    }

    @PostMapping("/")
    public ResponseEntity<Long> resgister(@RequestBody TodoDTO dto) {
        return ResponseEntity.ok().body(todoService.register(dto));
    }
    @GetMapping("/{tno}")
    public ResponseEntity<TodoDTO> read(@PathVariable Long tno) {
        return ResponseEntity.ok().body(todoService.read(tno));
    }

    @DeleteMapping("/{tno}")
    public ResponseEntity<Long> remove(@PathVariable Long tno) {
        return ResponseEntity.ok().body(todoService.remove(tno));
    }

    @PutMapping("/{tno}")
    public ResponseEntity<TodoDTO> modify(@PathVariable Long tno ,@RequestBody TodoDTO dto) {
        dto.setTno(tno);
        System.out.println(dto);
        return ResponseEntity.ok().body(todoService.modify(dto));
    }

}
