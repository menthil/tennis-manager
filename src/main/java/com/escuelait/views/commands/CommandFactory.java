package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;

class CommandFactory {

  private CommandFactory() {
  }

  static Command create(com.escuelait.controllers.Command command, Controller controller, String prompt) {
    return switch (command) {
      case CREATE_REFEREE -> new CreateRefereeCommand(controller, prompt);
      case EXIT -> new ExitCommand(controller, prompt);
      case LOGIN -> new LoginCommand(controller, prompt);
      case HELP -> new HelpCommand(controller, prompt);
      case CREATE_PLAYER -> new CreatePlayerCommand(controller, prompt);
      case READ_PLAYERS -> new ReadPlayersCommand(controller, prompt);
      case CREATE_MATCH -> new CreateMatchCommand(controller, prompt);
      case LACK_SERVICE -> new LackServiceCommand(controller, prompt);
      case POINT_SERVICE -> new PointServiceCommand(controller, prompt);
      case POINT_REST -> new PointRestCommand(controller, prompt);
      case FINISH_MATCH -> new FinishMatchCommand(controller, prompt);
      case READ_PLAYER -> new ReadPlayerCommand(controller, prompt);
      case READ_MATCH -> new ReadMatchCommand(controller, prompt);
      case LOGOUT -> new LogoutCommand(controller, prompt);
    };
  }

}
