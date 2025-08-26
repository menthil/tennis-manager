package com.escuelait.controllers;

import java.util.List;

import com.escuelait.models.State;
import com.escuelait.repositories.PlayerRepository;

public class ManageController extends Controller {

  private static final int MIN_LENGTH = 4;
  private PlayerRepository playerRepository;

  ManageController(State state, PlayerRepository playerRepository) {
    super(state);
    this.playerRepository = playerRepository;
  }

  @Override
  public void accept(ControllerVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public List<CommandType> getAvailableCommandTypes() {
    return List.of(
        CommandType.CREATE_PLAYER,
        CommandType.READ_PLAYERS,
        CommandType.CREATE_MATCH,
        CommandType.READ_PLAYER,
        CommandType.READ_MATCH,
        CommandType.LOGOUT,
        CommandType.HELP,
        CommandType.EXIT);
  }

  public List<String> getCreatePlayerErrors(String name) {
    assert name != null;
    if (name.length() < ManageController.MIN_LENGTH) {
      return List.of("La longitud del nombre debe ser al menos " + ManageController.MIN_LENGTH);
    }
    if (this.playerRepository.containsName(name)) {
      return List.of("El jugador ya existe");
    }
    return List.of();
  }

  public void createPlayer(String name) {
    assert this.getCreatePlayerErrors(name).isEmpty();
    this.playerRepository.create(name);
  }

}
