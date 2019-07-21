package com.example.checkers.adapter.out;

import com.example.checkers.adapter.out.dto.BoardDto;
import com.example.checkers.adapter.out.mapper.MapperOut;
import com.example.checkers.domain.CallbackInterface;
import com.example.checkers.domain.entity.Board;
import com.example.checkers.domain.entity.Player;
import com.example.checkers.game.Cell;
import com.example.checkers.game.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GameRepository implements IGameRepository {

  private final static List<Player> LIST = new CopyOnWriteArrayList<Player>();
  private final static Map<Integer, CallbackInterface> READY_PLAYERS = new ConcurrentHashMap<>();
  private final static List<BoardDto> GAMES = new ArrayList<>();
  private final MapperOut mapper;

  @Override
  public void join(Player player, CallbackInterface callbackInterface) {
    LIST.add(player);
    READY_PLAYERS.put(player.getId(), callbackInterface);
  }

  @Override
  public Board getBoardById(Integer id) {
    return mapper.fromBoardDtoToEntity(GAMES.get(id));
  }

  @Override
  public void updateBoard(Board board) {
    BoardDto boardDto = mapper.fromBoardToBoardDto(board);
    GAMES.set(boardDto.getId(), boardDto);
  }

  @Scheduled(fixedDelay = 5000)
  private void cron() {
    for (; READY_PLAYERS.size() > 1; ) {
      List<Integer> list = READY_PLAYERS.keySet().stream().limit(2)
          .collect(Collectors.toList());
      Collections.shuffle(list);
      Integer whiteKey = list.get(0);
      CallbackInterface white = READY_PLAYERS.get(whiteKey);
      READY_PLAYERS.remove(whiteKey);
      Integer blackKey = list.get(1);
      CallbackInterface black = READY_PLAYERS.get(blackKey);
      READY_PLAYERS.remove(blackKey);
      Map<Color, Integer> players = new HashMap<>();
      players.put(Color.WHITE, whiteKey);
      players.put(Color.BLACK, blackKey);
      BoardDto boardDto = BoardDto.builder()
          .id(GAMES.size())
          .players(players)
          .state(defaultState())
          .stepNumber(0)
          .lastStep(null)
          .build();
      black.doSomething(mapper.fromBoardDtoToEntity(boardDto));
      white.doSomething(mapper.fromBoardDtoToEntity(boardDto));
    }
  }

  private Cell[][] defaultState() {
    int size = 8;
    Cell[][] defaultState = new Cell[size][size];
    for (int i = 1; i <= size; i++) {
      for (int j = 1; j <= size; j++) {
        Color color = getColor(i, j);
        defaultState[i - 1][j - 1] = Cell.builder()
            .color(color)
            .player(color.equals(Color.BLACK) ? getPlayerColor(i, size) : null)
            .build();
      }
    }
    return defaultState;
  }

  private Color getPlayerColor(int j, int size) {
    if (j <= 3) {
      return Color.BLACK;
    }
    if (j >= size - 2) {
      return Color.WHITE;
    }
    return null;
  }

  private Color getColor(int i, int j) {
    if (j % 2 == 0) {
      return i % 2 == 0 ? Color.WHITE : Color.BLACK;
    } else {
      return i % 2 == 0 ? Color.BLACK : Color.WHITE;
    }
  }
}
