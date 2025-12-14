package com.escuelait.views.commands;

class CommandFactory {

  private CommandFactory() {
  }

  static Command create(com.escuelait.controllers.Command command) {
    return switch (command) {
      case CREATE_REFEREE -> new CreateRefereeCommand();
      case EXIT -> new ExitCommand();
      case LOGIN -> new LoginCommand();
      case HELP -> new HelpCommand();
      case CREATE_PLAYER -> new CreatePlayerCommand();
      case READ_PLAYERS -> new ReadPlayersCommand();
      case CREATE_MATCH -> new CreateMatchCommand();
      case LACK_SERVICE -> new LackServiceCommand();
      case POINT_SERVICE -> new PointServiceCommand();
      case POINT_REST -> new PointRestCommand();
      case FINISH_MATCH -> new FinishMatchCommand();
      case READ_PLAYER -> new ReadPlayerCommand();
      case READ_MATCH -> new ReadMatchCommand();
      case LOGOUT -> new LogoutCommand();
    };
  }

}
