package com.example.checkers.adapter.in;

import com.example.checkers.adapter.in.dto.RegistrationDto;
import com.example.checkers.adapter.in.mapper.MapperIn;
import com.example.checkers.domain.IPlayerService;
import com.example.checkers.domain.valueobject.Registration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {

  private final IPlayerService playerService;
  private final MapperIn mapperIn;

  @PostMapping("/registration")
  public int registration(@RequestBody RegistrationDto registrationDto) {
    Registration registration = mapperIn.fromDtoToEntity(registrationDto);
    return playerService.registration(registration);
  }
}
