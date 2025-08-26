package com.escuelait.views;

import java.util.List;

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
    LoginController loginController = ((LoginController) this.controller);
    List<String> errors = loginController.getCreateRefereeeErrors(this.name, this.password);
    if (!errors.isEmpty()) {
      for (String error : errors) {
        this.console.writeln(error);
      }
    } else {
      loginController.createReferee(this.name, this.password);
      this.console.writeln("Árbitro creado correctamente");
    }
  }

}
