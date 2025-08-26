package com.escuelait.views;

import com.escuelait.controllers.CommandType;
import com.escuelait.controllers.Controller;
import com.escuelait.controllers.LoginController;

class CreateRefereeCommand extends Command {

  private String name;
  private String password;

  CreateRefereeCommand(Controller controller, String commandString) {
    super(controller);
    this.name = CommandType.CREATE_REFEREE.getArgs(commandString).get(0);
    this.password = CommandType.CREATE_REFEREE.getArgs(commandString).get(1);
  }

  @Override
  void execute() {
    ((LoginController) this.controller).createReferee(this.name, this.password);
  }

}
