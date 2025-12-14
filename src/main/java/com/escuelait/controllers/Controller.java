package com.escuelait.controllers;

import java.util.List;
import com.escuelait.models.Match;
import com.escuelait.models.State;

public abstract class Controller {

  protected State state;

  Controller(State state) {
    this.state = state;
  }

  public abstract void accept(ControllerVisitor visitor);

  public abstract List<Command> getAvailableCommands();

  public void started() {
    this.state.started();
  }

  public Match getMatch() {
    return this.state.getMatch();
  }

  public void exit() {
    this.state.exit();
  }

  public boolean isMatchStarted() {
    return this.state.isMatchStarted();
  }

}
