package com.example.checkers.game;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cell {

  private Color color;
  private Color player;

}
