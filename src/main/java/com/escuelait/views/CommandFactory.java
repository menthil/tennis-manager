package com.escuelait.views;

import com.escuelait.controllers.Controller;

class CommandFactory {

  private CommandFactory() {
  }

  static Command create(Controller controller, String commandString) {
    switch (controller.getCommandType(commandString)) {
      case CREATE_REFEREE:
        return new CreateRefereeCommand(controller, commandString);
      case EXIT:
        return new ExitCommand(controller);
      case LOGIN:
        return null;
      default:
        return null;
    }
  }

}
