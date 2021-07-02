package org.zerock.j07.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.repository.dynamic.BoardSearchRepository;

//168
public interface BoardRepository extends JpaRepository<Board,Long>, BoardSearchRepository {

    @Query("select b,count(distinct r),count(distinct f) from " +
            "Board b left join Reply r on r.board = b " +
            "left join Favorite f on f.board = b " +
            "group by b order by b.bno desc")
    Page<Object[]> getData1(Pageable pageable);

}
