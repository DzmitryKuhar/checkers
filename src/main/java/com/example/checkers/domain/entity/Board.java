package com.example.checkers.domain.entity;

import com.example.checkers.game.Cell;
import com.example.checkers.game.Color;
import lombok.Data;

import java.util.Map;

@Data
public class Board {

  private Integer id;

  private Cell[][] state;

  private Map<Color, Integer> players;

  private Integer stepNumber;

  private Integer lastStep;

}
