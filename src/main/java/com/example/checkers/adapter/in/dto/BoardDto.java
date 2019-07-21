package com.example.checkers.adapter.in.dto;

import com.example.checkers.game.Cell;
import com.example.checkers.game.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

  private Integer id;

  private Cell[][] state;

  private Map<Color, Integer> players;

}
