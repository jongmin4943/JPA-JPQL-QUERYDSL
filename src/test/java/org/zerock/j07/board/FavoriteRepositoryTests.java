package org.zerock.j07.board;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.entity.Favorite;
import org.zerock.j07.board.repository.FavoriteRepository;

import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("dev")
@Log4j2
public class FavoriteRepositoryTests {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Test
    public void FavDummies() {

        IntStream.rangeClosed(1,500).forEach(i -> {
            Long bno =(long)(Math.random()*200)+1;
            Board board = Board.builder()
                    .bno(bno).build();
            Favorite favorite = Favorite.builder()
                            .board(board)
                            .mark(true)
                            .actor("user00")
                            .build();
            favoriteRepository.save(favorite);
        });


    }
}
