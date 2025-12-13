package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;

public class CommandFactory {

  private CommandFactory() {
  }

  public static Command create(Controller controller, String prompt) {
    assert controller != null && prompt != null;
    switch (controller.getCommand(prompt)) {
      case CREATE_REFEREE:
        return new CreateRefereeCommand(controller, prompt);
      case EXIT:
        return new ExitCommand(controller);
      case LOGIN:
        return new LoginCommand(controller, prompt);
      case HELP:
        return new HelpCommand(controller);
      case CREATE_PLAYER:
        return new CreatePlayerCommand(controller, prompt);
      case READ_PLAYERS:
        return new ReadPlayersCommand(controller);
      case CREATE_MATCH:
        return new CreateMatchCommand(controller, prompt);
      case LACK_SERVICE:
        return new LackServiceCommand(controller);
      case POINT_SERVICE:
        return new PointServiceCommand(controller);
      case POINT_REST:
        return new PointRestCommand(controller);
      case FINISH_MATCH:
        return new FinishMatchCommand(controller);
      case READ_PLAYER:
        return new ReadPlayerCommand(controller, prompt);
      case READ_MATCH:
        return new ReadMatchCommand(controller, prompt);
      case LOGOUT:
        return new LogoutCommand(controller);
      default:
        return null;
    }
  }

}
