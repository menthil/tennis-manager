package com.escuelait.controllers;

import java.util.List;

import com.escuelait.models.State;

public class ManageController extends Controller {

  ManageController(State state) {
    super(state);
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

}
