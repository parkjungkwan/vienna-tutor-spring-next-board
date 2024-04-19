package com.bitcamp.api.board.service;

import java.util.Optional;

import com.bitcamp.api.board.model.Board;
import com.bitcamp.api.board.model.BoardDto;
import com.bitcamp.api.common.service.CommandService;
import com.bitcamp.api.common.service.QueryService;

public interface BoardService extends CommandService<BoardDto>, QueryService<BoardDto>{
    default Board dtoToEntity(BoardDto dto){
        return Board.builder()
        .id(dto.getId())
        .title(dto.getTitle())
        .description(dto.getDescription())
        .build();
    }

    default BoardDto entityToDto(Board entity){
        return BoardDto.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        // .regDate(entity.getRegDate().toString())
        // .modDate(entity.getModDate().toString())
        .build();
    }
}
