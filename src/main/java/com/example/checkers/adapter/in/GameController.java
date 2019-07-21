package com.example.checkers.adapter.in;

import com.example.checkers.adapter.in.dto.BoardDto;
import com.example.checkers.adapter.in.dto.StepDto;
import com.example.checkers.adapter.in.mapper.MapperIn;
import com.example.checkers.domain.IGameService;
import com.example.checkers.domain.entity.Board;
import com.example.checkers.domain.valueobject.Step;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

  private final MapperIn mapper;
  private final IGameService gameService;

  @GetMapping("/join")
  public BoardDto join(@RequestParam("id") Integer playerId) {
    Board board = gameService.join(playerId);
    return mapper.fromBoardEntityToBoardDto(board);
  }

  @PatchMapping("/step")
  public BoardDto step(@RequestBody StepDto stepDto) {
    Step step = mapper.fromStepDtoToStep(stepDto);
    return mapper.fromBoardEntityToBoardDto(gameService.step(step));
  }

  @GetMapping("wait")
  public BoardDto waitStep(@RequestParam("boardId") Integer boardId,
      @RequestParam("playerId") Integer playerId) {
    return mapper.fromBoardEntityToBoardDto(gameService.waitStep(boardId, playerId));
  }

  @GetMapping
  public void end(@RequestParam("boardId") Integer boardId,
      @RequestParam("playerId") Integer playerId) {
    gameService.end(boardId, playerId);
  }

}
