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
    return CommandFactory.create(matched.get()).isValid(this.prompt)
        ? List.of()
        : List.of("Parámetros incorrectos: "
            + CommandFactory.create(matched.get()).getSyntax());
  }

  private Optional<com.escuelait.controllers.Command> getMatchedCommand() {
    for (com.escuelait.controllers.Command command : this.controller.getAvailableCommands()) {
      if (CommandFactory.create(command).is(this.prompt)) {
        return Optional.of(command);
      }
    }
    return Optional.empty();
  }

  public Command getCommand() {
    assert this.getErrors(this.prompt).isEmpty();
    return CommandFactory.create(this.getMatchedCommand().get());
  }

}
