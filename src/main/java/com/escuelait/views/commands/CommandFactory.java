package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;

public class CommandFactory {

  private CommandFactory() {
  }

  public static Command create(Controller controller, String commandString) {
    assert controller != null && commandString != null;
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
      case POINT_SERVICE:
        return new PointServiceCommand(controller);
      case POINT_REST:
        return new PointRestCommand(controller);
      case FINISH_MATCH:
        return new FinishMatchCommand(controller);
      case READ_PLAYER:
        return new ReadPlayerCommand(controller, commandString);
      case READ_MATCH:
        return new ReadMatchCommand(controller, commandString);
      case LOGOUT:
        return new LogoutCommand(controller);
      default:
        return null;
    }
  }

}
