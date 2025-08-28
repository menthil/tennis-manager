package com.escuelait.controllers;

import java.util.List;
import java.util.Random;

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
        CommandType.FINISH_MATCH,
        CommandType.HELP,
        CommandType.EXIT);
  }

  public void lackService() {
    this.state.lackService();
  }

  public void addPointService() {
    this.state.addPointService();
  }

  public void addPointRest() {
    this.state.addPointRest();
  }

  public void finishMatch() {
    Random random = new Random();
    do {
      switch (Double.valueOf(random.nextDouble())) {
        case Double d when d < 0.7 -> this.addPointService();
        case Double d when d < 0.9 -> this.addPointRest();
        default -> this.lackService();
      }
    } while (!this.state.isFinished());
  }

}
