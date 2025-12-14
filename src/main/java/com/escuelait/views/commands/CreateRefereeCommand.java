package com.escuelait.views.commands;

import java.util.List;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.LoginController;

class CreateRefereeCommand extends Command {

  private String name;
  private String password;

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
  public void execute(Controller controller, String prompt) {
    this.name = this.getArgs(prompt).get(0);
    this.password = this.getArgs(prompt).get(1);
    LoginController loginController = ((LoginController) controller);
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
