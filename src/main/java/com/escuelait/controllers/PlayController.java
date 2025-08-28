package com.escuelait.controllers;

import java.util.List;

import com.escuelait.models.State;

public class PlayController extends Controller {

  PlayController(State state) {
    super(state);
  }

  @Override
  public void accept(ControllerVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public List<CommandType> getAvailableCommandTypes() {
    return List.of(
        CommandType.LACK_SERVICE,
        CommandType.POINT_SERVICE,
        CommandType.POINT_REST,
        CommandType.HELP,
        CommandType.EXIT);
  }

  public void lackService() {
    this.state.lackService();
  }

  public void addPointService() {
    this.state.addPointService();
  }

}
