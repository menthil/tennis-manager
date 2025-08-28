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
        return new LoginCommand(controller, commandString);
      case HELP:
        return new HelpCommand(controller);
      case CREATE_PLAYER:
        return new CreatePlayerCommand(controller, commandString);
      case READ_PLAYERS:
        return new ReadPlayersCommand(controller);
      case CREATE_MATCH:
        return new CreateMatchCommand(controller, commandString);
      case LACK_SERVICE:
        return new LackServiceCommand(controller);
      default:
        return null;
    }
  }

}
