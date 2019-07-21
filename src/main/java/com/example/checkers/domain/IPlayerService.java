package com.example.checkers.domain;

import com.example.checkers.domain.entity.Player;
import com.example.checkers.domain.valueobject.Registration;

public interface IPlayerService {

  int registration(Registration registration);

  Player getPlayerById(Integer playerId);
}
