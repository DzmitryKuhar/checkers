package com.example.checkers.adapter.out;

import com.example.checkers.adapter.out.dto.PlayerDto;
import com.example.checkers.adapter.out.mapper.MapperOut;
import com.example.checkers.domain.entity.Player;
import com.example.checkers.domain.valueobject.Registration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class PlayerRepository implements IPlayerRepository {

  private final MapperOut mapperOut;
  private static final Map<Integer, PlayerDto> PLAYERS = new ConcurrentHashMap<>();

  @Override
  public Player registration(Registration registration) {
    PlayerDto playerDto = mapperOut.fromEntityToDto(registration);
    createPlayer(playerDto);
    return mapperOut.fromDtoToEntity(playerDto);
  }

  @Override
  public Player getById(Integer id) {
    PlayerDto playerDto = PLAYERS.get(id);
    return mapperOut.fromDtoToEntity(playerDto);
  }

  private void createPlayer(PlayerDto playerDto) {
    Integer id = PLAYERS.size();
    playerDto.setId(id);
    PLAYERS.put(id, playerDto);
  }
}
