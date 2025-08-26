package com.escuelait.controllers;

import java.util.List;
import java.util.Optional;

import com.escuelait.models.State;

public abstract class Controller {

  protected State state;

  Controller(State state) {
    this.state = state;
  }

  public abstract void accept(ControllerVisitor visitor);

  public List<String> getErrors(String commandString) {
    assert commandString != null;
    Optional<CommandType> matched = this.getMatchedCommandType(commandString);
    if (matched.isEmpty()) {
      return List.of("Comando no válido");
    }
    return matched.get().isValid(commandString)
        ? List.of()
        : List.of("Parámetros incorrectos: " + matched.get().getCommand());
  }

  private Optional<CommandType> getMatchedCommandType(String commandString) {
    for (CommandType commandType : this.getAvailableCommandTypes()) {
      if (commandType.is(commandString)) {
        return Optional.of(commandType);
      }
    }
    return Optional.empty();
  }

  public abstract List<CommandType> getAvailableCommandTypes();

  public CommandType getCommandType(String commandString) {
    assert this.getErrors(commandString).isEmpty();
    return this.getMatchedCommandType(commandString).get();
  }

  public void exit() {
    this.state.exit();
  }

}
