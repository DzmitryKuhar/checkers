package com.example.checkers.domain;

import com.example.checkers.adapter.out.IGameRepository;
import com.example.checkers.domain.entity.Player;
import com.example.checkers.domain.entity.Board;
import com.example.checkers.domain.valueobject.Step;
import com.example.checkers.game.Cell;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class GameService implements IGameService {

  private final IPlayerService playerService;
  private final IGameRepository gameRepository;

  //private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2);

  //private static final int CHECK_PERIOD_MILLIS = 5;

  @Override
  public Board join(Integer playerId) {
    Player player = playerService.getPlayerById(playerId);
    final Board[] board = {null};
    CountDownLatch countDownLatch = new CountDownLatch(1);
    gameRepository.join(player, param -> {
      board[0] = param;
      countDownLatch.countDown();
    });
    try {
      countDownLatch.await(60, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //executorService.scheduleAtFixedRate(null, 0, CHECK_PERIOD_MILLIS, TimeUnit.SECONDS);
    return board[0];
  }

  @Override
  public Board step(Step step) {
    //validation part here
    Board board = gameRepository.getBoardById(step.getBoardId());
    //board validation
    Cell[][] state = board.getState();
    //step rights validation
    String[] fromCoordinates = step.getFrom().split(":");
    Cell cellFrom = state[Integer.parseInt(fromCoordinates[0])][Integer.parseInt(fromCoordinates[1])];
    Cell cellTo = null;
    if (step.getTo().length == 1) {
      String[] toCoordinates = step.getTo()[0].split(":");
      cellTo = state[Integer.parseInt(toCoordinates[0])][Integer.parseInt(toCoordinates[1])];
    }
    cellTo.setPlayer(cellFrom.getPlayer());
    cellFrom.setPlayer(null);
    board.setStepNumber(board.getStepNumber() + 1);
    board.setLastStep(step.getPlayerId());
    gameRepository.updateBoard(board);
    return board;
  }

  @Override
  public Board waitStep(Integer boardId, Integer playerId) {
    return null;
  }

  @Override
  public void end(Integer boardId, Integer playerId) {
    //gameRepository.
  }
}

class CheckHelper implements Runnable {

  @Override
  public void run() {

  }
}
