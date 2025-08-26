package com.escuelait.views;

import com.escuelait.controllers.CommandType;
import com.escuelait.controllers.Controller;

class HelpCommand extends Command {

  HelpCommand(Controller controller) {
    super(controller);
  }

  @Override
  void execute() {
    this.console.writeln("Comandos disponibles:");
    this.console.writeln();
    for (CommandType commandType : controller.getAvailableCommandTypes()) {
      this.console.writeln(commandType.getCommand());
    }
  }

}
