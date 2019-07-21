package com.example.checkers.domain;

import com.example.checkers.adapter.out.IPlayerRepository;
import com.example.checkers.domain.entity.Player;
import com.example.checkers.domain.valueobject.Registration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService implements IPlayerService {

  private final IPlayerRepository playerRepository;

  @Override
  public int registration(Registration registration) {
    Player player = playerRepository.registration(registration);
    return player.getId();
  }

  @Override
  public Player getPlayerById(Integer playerId) {
    return playerRepository.getById(playerId);
  }
}
