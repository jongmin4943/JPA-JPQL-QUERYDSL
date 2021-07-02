package org.zerock.j07.board.service;

import org.zerock.j07.board.dto.BoardDTO;
import org.zerock.j07.board.dto.BoardListRequestDTO;
import org.zerock.j07.board.dto.ListBoardDTO;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.ListRequestDTO;

public interface BoardService {

    ListResponseDTO<ListBoardDTO> getList(BoardListRequestDTO listRequestDTO);

    default BoardDTO entityToDTO(Board board) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .content(board.getContent())
                .title(board.getTitle())
                .writer(board.getWriter())
                .modDate(board.getModDate())
                .regDate(board.getRegDate())
                .build();
    }
    default ListBoardDTO arrToDTO(Object[] arr) {
        Board board = (Board) arr[0];
        long replyCount = (long) arr[1];
        long favCount = (long) arr[2];
        return ListBoardDTO.builder()
                .boardDTO(entityToDTO(board))
                .likeCount(favCount)
                .replyCount(replyCount)
                .build();
    }
}
