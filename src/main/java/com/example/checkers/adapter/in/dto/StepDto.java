package com.example.checkers.adapter.in.dto;

import lombok.Data;

@Data
public class StepDto {

  private Integer boardId;

  private Integer playerId;

  private String from;

  private String[] to;

}
