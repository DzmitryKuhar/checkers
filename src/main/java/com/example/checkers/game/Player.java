package com.example.checkers.game;

import lombok.Data;

@Data
public class Player {

  private Integer id;

  private String name;

  private boolean isActive;

  private Color color;
}
