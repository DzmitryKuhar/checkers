package com.example.checkers.domain;

import com.example.checkers.domain.entity.Board;
import com.example.checkers.domain.valueobject.Step;

public interface IGameService {

  Board join(Integer playerId);

  Board step(Step step);

  Board waitStep(Integer boardId, Integer playerId);

  void end(Integer boardId, Integer playerId);
}
