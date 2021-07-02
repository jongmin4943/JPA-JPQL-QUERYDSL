package org.zerock.j07.todo.service;

import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.ListRequestDTO;
import org.zerock.j07.todo.dto.TodoDTO;
import org.zerock.j07.todo.entity.Todo;

public interface TodoService {

    ListResponseDTO<TodoDTO> list(ListRequestDTO dto);

    long register(TodoDTO dto);

    TodoDTO read(Long tno);


    default Todo dtoToEntity(TodoDTO dto) {
        return Todo.builder()
                .tno(dto.getTno())
                .content(dto.getContent())
                .del(dto.isDel())
                .build();
    }
    default TodoDTO entityToDTO(Todo entity) {
        return TodoDTO.builder()
                .tno(entity.getTno())
                .content(entity.getContent())
                .del(entity.isDel())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }

    Long remove(Long tno);

    TodoDTO modify(TodoDTO dto);
}
