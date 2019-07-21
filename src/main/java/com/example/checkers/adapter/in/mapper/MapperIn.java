package com.example.checkers.adapter.in.mapper;

import com.example.checkers.adapter.in.dto.BoardDto;
import com.example.checkers.adapter.in.dto.RegistrationDto;
import com.example.checkers.adapter.in.dto.StepDto;
import com.example.checkers.domain.entity.Board;
import com.example.checkers.domain.valueobject.Registration;
import com.example.checkers.domain.valueobject.Step;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperIn {

  private final ModelMapper modelMapper;

  public Registration fromDtoToEntity(RegistrationDto registrationDto) {
    return modelMapper.map(registrationDto, Registration.class);
  }

  public BoardDto fromBoardEntityToBoardDto(Board board) {
    return modelMapper.map(board, BoardDto.class);
  }

  public Step fromStepDtoToStep(StepDto stepDto) {
    return modelMapper.map(stepDto, Step.class);
  }
}
