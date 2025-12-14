package com.escuelait.views.commands;

import java.util.List;
import java.util.Optional;

import com.escuelait.controllers.Controller;

public class CommandAnalyzer {

  private Controller controller;
  private String prompt;

  public CommandAnalyzer(Controller controller) {
    this.controller = controller;
  }

  public List<String> getErrors(String prompt) {
    assert prompt != null;
    this.prompt = prompt;
    Optional<com.escuelait.controllers.Command> matched = this.getMatchedCommand();
    if (matched.isEmpty()) {
      return List.of("Comando no válido");
    }
    return matched.get().isValid(this.prompt)
        ? List.of()
        : List.of("Parámetros incorrectos: " + matched.get().getSyntax());
  }

  private Optional<com.escuelait.controllers.Command> getMatchedCommand() {
    for (com.escuelait.controllers.Command command : this.controller.getAvailableCommands()) {
      if (command.is(this.prompt)) {
        return Optional.of(command);
      }
    }
    return Optional.empty();
  }

  public Command getCommand() {
    assert this.getErrors(this.prompt).isEmpty();
    switch (this.getMatchedCommand().get()) {
      case CREATE_REFEREE:
        return new CreateRefereeCommand(this.controller, this.prompt);
      case EXIT:
        return new ExitCommand(this.controller);
      case LOGIN:
        return new LoginCommand(this.controller, this.prompt);
      case HELP:
        return new HelpCommand(this.controller);
      case CREATE_PLAYER:
        return new CreatePlayerCommand(this.controller, this.prompt);
      case READ_PLAYERS:
        return new ReadPlayersCommand(this.controller);
      case CREATE_MATCH:
        return new CreateMatchCommand(this.controller, this.prompt);
      case LACK_SERVICE:
        return new LackServiceCommand(this.controller);
      case POINT_SERVICE:
        return new PointServiceCommand(this.controller);
      case POINT_REST:
        return new PointRestCommand(this.controller);
      case FINISH_MATCH:
        return new FinishMatchCommand(this.controller);
      case READ_PLAYER:
        return new ReadPlayerCommand(this.controller, this.prompt);
      case READ_MATCH:
        return new ReadMatchCommand(this.controller, this.prompt);
      case LOGOUT:
        return new LogoutCommand(this.controller);
      default:
        return null;
    }
  }

}
