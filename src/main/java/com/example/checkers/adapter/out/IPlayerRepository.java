package com.example.checkers.adapter.out;

import com.example.checkers.domain.entity.Player;
import com.example.checkers.domain.valueobject.Registration;

public interface IPlayerRepository {

  Player registration(Registration registration);

  Player getById(Integer id);
}
