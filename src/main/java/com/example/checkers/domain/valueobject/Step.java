package com.example.checkers.domain.valueobject;

import lombok.Data;

@Data
public class Step {

  private Integer boardId;

  private Integer playerId;

  private String from;

  private String[] to;

}
