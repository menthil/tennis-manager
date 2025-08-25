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

  public boolean isValid(String commandString) {
    assert commandString != null;
    return this.getValidated(commandString).isPresent();
  }

  private Optional<CommandType> getValidated(String commandString) {
    for (CommandType commandType : this.getAvailableCommandTypes()) {
      if (commandType.isValid(commandString)) {
        return Optional.of(commandType);
      }
    }
    return Optional.empty();
  }

  protected abstract List<CommandType> getAvailableCommandTypes();

  public CommandType getCommandType(String commandString) {
    assert this.isValid(commandString);
    return this.getValidated(commandString).get();
  }

  public void exit() {
    this.state.exit();
  }

}
