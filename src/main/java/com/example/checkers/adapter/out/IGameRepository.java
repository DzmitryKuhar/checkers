package com.example.checkers.adapter.out;

import com.example.checkers.domain.CallbackInterface;
import com.example.checkers.domain.entity.Board;
import com.example.checkers.domain.entity.Player;

public interface IGameRepository {

  void join(Player player, CallbackInterface callbackInterface);

  Board getBoardById(Integer id);

  void updateBoard(Board board);
}
