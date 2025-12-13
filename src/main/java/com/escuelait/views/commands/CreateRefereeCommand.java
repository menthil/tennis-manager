package com.escuelait.views.commands;

import java.util.List;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.LoginController;

class CreateRefereeCommand extends Command {

  private String name;
  private String password;

  CreateRefereeCommand(Controller controller, String prompt) {
    super(controller);
    this.name = com.escuelait.controllers.Command.CREATE_REFEREE.getArgs(prompt).get(0);
    this.password = com.escuelait.controllers.Command.CREATE_REFEREE.getArgs(prompt).get(1);
  }

  @Override
  public void execute() {
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
