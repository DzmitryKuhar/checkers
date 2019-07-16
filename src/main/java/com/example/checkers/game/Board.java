package com.example.checkers.game;

import lombok.Data;

import java.util.HashMap;

@Data
public class Board {

  private Integer id;

  private Cell[][] state;

  private HashMap<Color, Player> players;

}
