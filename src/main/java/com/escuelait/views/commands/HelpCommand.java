package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;

class HelpCommand extends Command {

  HelpCommand(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    this.console.writeln("Comandos disponibles:");
    this.console.writeln();
    for (com.escuelait.controllers.Command commandType : controller.getAvailableCommands()) {
      this.console.writeln(commandType.getSyntax());
    }
  }

}
