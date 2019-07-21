package com.example.checkers.domain.entity;

import com.example.checkers.game.Color;
import lombok.Data;

@Data
public class Player {

  private Integer id;

  private String username;

  private boolean isActive;

  private Color color;
}
