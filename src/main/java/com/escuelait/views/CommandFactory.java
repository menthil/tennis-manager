package com.escuelait.views;

import com.escuelait.controllers.CommandType;

class CommandFactory {

  private CommandFactory() {
  }

  static Command create(CommandType commandType, String commandString) {
    switch (commandType) {
      case CREATE_REFEREE:
        return new CreateRefereeCommand(commandString);
      case EXIT:
        return new ExitCommand();
      case LOGIN:
        return null;
      default:
        return null;
    }
  }

}
