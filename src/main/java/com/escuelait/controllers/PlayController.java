package com.escuelait.controllers;

import java.util.List;
import java.util.Random;

import com.escuelait.models.State;
import com.escuelait.repositories.MatchRepository;

public class PlayController extends Controller {

  private MatchRepository matchRepository;

  PlayController(State state, MatchRepository matchRepository) {
    super(state);
    this.matchRepository = matchRepository;
  }

  @Override
  public void accept(ControllerVisitor visitor) {
    assert visitor != null;
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
    this.saveIfFinished();
  }

  private void saveIfFinished() {
    if (this.isFinished()) {
      this.save();
    }
  }

  public boolean isFinished() {
    return this.state.isFinished();
  }

  private void save() {
    this.matchRepository.save(this.state.getMatch());
  }

  public void addPointService() {
    this.state.addPointService();
    this.saveIfFinished();
  }

  public void addPointRest() {
    this.state.addPointRest();
    this.saveIfFinished();
  }

  public void finishMatch() {
    final double[] PROBABILITIES = new double[] { 0.7, 0.9 };
    Random random = new Random();
    do {
      switch (Double.valueOf(random.nextDouble())) {
        case Double d when d < PROBABILITIES[0] -> this.addPointService();
        case Double d when d < PROBABILITIES[1] -> this.addPointRest();
        default -> this.lackService();
      }
    } while (!this.isFinished());
  }

  public void logged() {
    this.state.logged();
  }

}
