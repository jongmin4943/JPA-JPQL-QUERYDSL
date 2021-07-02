package org.zerock.j07.board;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.repository.BoardRepository;

import java.util.Arrays;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
@ActiveProfiles("dev")
public class BoardRepositoryTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1,200).forEach(e->{
            Board board = Board.builder()
                    .title("제목..."+e)
                    .content("내용..."+e)
                    .writer("user00")
                    .build();
            boardRepository.save(board);
            log.info(board);
            System.out.println(board);
        });//end loop
    }

    @Test
    public void testGet1() {
        Pageable pageable = PageRequest.of(0,10);
        Page<Object[]> result = boardRepository.getData1(pageable);

        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }

    @Test
    public void testSearch() {
        Pageable pageable = PageRequest.of(0,10);
        String type = "tcw";
        String keyword = "1";
        Page<Object[]> result = boardRepository.getSearchList(type,keyword,pageable);
    }
}
