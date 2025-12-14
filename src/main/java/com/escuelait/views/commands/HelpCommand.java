package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;

class HelpCommand extends Command {

  HelpCommand(Controller controller, String prompt) {
    super(controller, prompt);
  }

  @Override
  protected String getName() {
    return "help";
  }

  @Override
  protected String getRegex() {
    return "";
  }

  @Override
  String getSyntax() {
    return "help";
  }

  @Override
  public void execute() {
    this.console.writeln("Comandos disponibles:");
    this.console.writeln();
    for (com.escuelait.controllers.Command commandType : controller.getAvailableCommands()) {
      this.console.writeln(CommandFactory.create(commandType, this.controller, this.prompt).getSyntax());
    }
  }

}
