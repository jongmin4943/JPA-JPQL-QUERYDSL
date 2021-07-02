package org.zerock.j07.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.PageMaker;
import org.zerock.j07.common.dto.ListRequestDTO;
import org.zerock.j07.todo.dto.TodoDTO;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;


    @Override
    public ListResponseDTO<TodoDTO> list(ListRequestDTO dto) {
        Pageable pageable = dto.getPageable();
        Page<Todo> result = todoRepository.listWithSearch(dto.getKeyword(),pageable);
        List<TodoDTO> dtoList = result.getContent().stream().map((todo) -> entityToDTO(todo)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(dto.getPage(),dto.getSize(), result.getTotalPages());

        return ListResponseDTO.<TodoDTO>builder()
                            .dtoList(dtoList)
                            .listRequestDTO(dto)
                            .pageMaker(pageMaker)
                            .build();

    }

    @Override
    public long register(TodoDTO dto) {
        Todo todo = dtoToEntity(dto);
        todoRepository.save(todo);
        return todo.getTno();
    }

    @Override
    public TodoDTO read(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);
        if(result.isPresent()){
            return entityToDTO(result.get());
        }
        return null;
    }

    @Override
    public Long remove(Long tno) {
        todoRepository.deleteById(tno);
        return tno;
    }

    @Override
    @Transactional
    public TodoDTO modify(TodoDTO dto) {
        Optional<Todo> result = todoRepository.findById(dto.getTno());
        if(result.isPresent()) {
            Todo entity = result.get();
            entity.changeTitle(dto.getContent());
            entity.changeDel(dto.isDel());
            return entityToDTO(entity);
        }
        return null;
    }
}
