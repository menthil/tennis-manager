package com.escuelait.views.commands;

import java.util.List;
import java.util.Optional;

import com.escuelait.controllers.Controller;
import com.escuelait.utils.Console;

public class CommandAnalyzer {

  private Controller controller;
  private String prompt;

  public CommandAnalyzer(Controller controller) {
    this.controller = controller;
  }

  public boolean isValid(String prompt) {
    assert prompt != null;
    this.prompt = prompt;
    List<String> errors = this.getErrors();
    for (String error : errors) {
      Console.getInstance().writeln(error);
    }
    return errors.isEmpty();
  }

  private List<String> getErrors() {
    Optional<Command> matched = this.getMatchedCommand();
    if (matched.isEmpty()) {
      return List.of("Comando no válido");
    }
    return matched.get().isValid(this.prompt)
        ? List.of()
        : List.of("Parámetros incorrectos: " + matched.get().getSyntax());
  }

  private Optional<Command> getMatchedCommand() {
    return this.controller.getAvailableCommands()
        .stream()
        .map(command -> CommandFactory.create(command))
        .filter(command -> command.is(this.prompt))
        .findFirst();
  }

  public void execute() {
    assert this.isValid(this.prompt);
    this.getMatchedCommand().get().execute(this.controller, this.prompt);
  }

}
