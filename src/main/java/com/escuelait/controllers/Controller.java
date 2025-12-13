package com.escuelait.controllers;

import java.util.List;
import java.util.Optional;

import com.escuelait.models.Match;
import com.escuelait.models.State;

public abstract class Controller {

  protected State state;

  Controller(State state) {
    this.state = state;
  }

  public abstract void accept(ControllerVisitor visitor);

  public List<String> getErrors(String prompt) {
    assert prompt != null;
    Optional<Command> matched = this.getMatchedCommand(prompt);
    if (matched.isEmpty()) {
      return List.of("Comando no válido");
    }
    return matched.get().isValid(prompt)
        ? List.of()
        : List.of("Parámetros incorrectos: " + matched.get().getSyntax());
  }

  private Optional<Command> getMatchedCommand(String prompt) {
    for (Command command : this.getAvailableCommands()) {
      if (command.is(prompt)) {
        return Optional.of(command);
      }
    }
    return Optional.empty();
  }

  public abstract List<Command> getAvailableCommands();

  public void started() {
    this.state.started();
  }

  public Command getCommand(String prompt) {
    assert this.getErrors(prompt).isEmpty();
    return this.getMatchedCommand(prompt).get();
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
