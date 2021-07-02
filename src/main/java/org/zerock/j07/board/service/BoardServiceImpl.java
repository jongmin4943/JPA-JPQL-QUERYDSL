package org.zerock.j07.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.zerock.j07.board.dto.BoardListRequestDTO;
import org.zerock.j07.board.dto.ListBoardDTO;
import org.zerock.j07.board.repository.BoardRepository;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.PageMaker;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public ListResponseDTO<ListBoardDTO> getList(BoardListRequestDTO listRequestDTO) {
        Page<Object[]> result = boardRepository.getSearchList(listRequestDTO.getType(),listRequestDTO.getKeyword(),listRequestDTO.getPageable());
        List<ListBoardDTO> boardDTOList = result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());
        PageMaker pageMaker = new PageMaker(listRequestDTO.getPage(),listRequestDTO.getSize(),(int)result.getTotalElements());
        return ListResponseDTO.<ListBoardDTO>builder()
                .dtoList(boardDTOList)
                .pageMaker(pageMaker)
                .listRequestDTO(listRequestDTO)
                .build();
    }
}
