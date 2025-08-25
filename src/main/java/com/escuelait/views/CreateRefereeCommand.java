package com.escuelait.views;

import com.escuelait.controllers.CommandType;
import com.escuelait.controllers.Controller;
import com.escuelait.controllers.LoginController;

class CreateRefereeCommand implements Command {

  private String name;
  private String password;

  CreateRefereeCommand(String commandString) {
    this.name = CommandType.CREATE_REFEREE.getArgs(commandString).get(0);
    this.password = CommandType.CREATE_REFEREE.getArgs(commandString).get(1);
  }

  @Override
  public void execute(Controller controller) {
    ((LoginController) controller).createReferee(this.name, this.password);
  }

}
