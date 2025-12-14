package com.escuelait.views.commands;

import java.util.List;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.LoginController;

class CreateRefereeCommand extends Command {

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
    String name = this.getArg(prompt, 0);
    String password = this.getArg(prompt, 1);
    LoginController loginController = ((LoginController) controller);
    List<String> errors = loginController.getCreateRefereeeErrors(name, password);
    if (!errors.isEmpty()) {
      for (String error : errors) {
        this.console.writeln(error);
      }
    } else {
      loginController.createReferee(name, password);
      this.console.writeln("Árbitro creado correctamente");
    }
  }

}
