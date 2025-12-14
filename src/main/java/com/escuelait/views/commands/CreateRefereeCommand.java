package com.escuelait.views.commands;

import java.util.List;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.LoginController;

class CreateRefereeCommand extends Command {

  private String name;
  private String password;

  CreateRefereeCommand(Controller controller, String prompt) {
    super(controller, prompt);
  }

  @Override
  protected String getName() {
    return "createReferee";
  }

  @Override
  protected String getRegex() {
    return "name:(.+);password:(.+)";
  }

  @Override
  String getSyntax() {
    return "createReferee name:<name>;password:<password>";
  }

  @Override
  public void execute() {
    this.name = this.getArgs().get(0);
    this.password = this.getArgs().get(1);
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
