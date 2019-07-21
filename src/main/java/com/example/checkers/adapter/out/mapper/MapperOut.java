package com.example.checkers.adapter.out.mapper;

import com.example.checkers.adapter.out.dto.BoardDto;
import com.example.checkers.adapter.out.dto.PlayerDto;
import com.example.checkers.domain.entity.Board;
import com.example.checkers.domain.entity.Player;
import com.example.checkers.domain.valueobject.Registration;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperOut {

  private final ModelMapper modelMapper;

  public PlayerDto fromEntityToDto(Registration registration) {
    return modelMapper.map(registration, PlayerDto.class);
  }

  public Player fromDtoToEntity(PlayerDto playerDto) {
    return modelMapper.map(playerDto, Player.class);
  }

  public Board fromBoardDtoToEntity(BoardDto boardDto) {
    return modelMapper.map(boardDto, Board.class);
  }

  public BoardDto fromBoardToBoardDto(Board board) {
    return modelMapper.map(board, BoardDto.class);
  }

}
