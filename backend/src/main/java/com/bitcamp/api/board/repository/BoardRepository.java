package com.bitcamp.api.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitcamp.api.board.model.Board;

@Repository
public interface BoardRepository  extends JpaRepository<Board, Long>{

    List<Board> findAllByOrderByContent();
    
}
