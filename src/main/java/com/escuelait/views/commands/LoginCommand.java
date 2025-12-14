package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.LoginController;

class LoginCommand extends Command {

  @Override
  protected String getName() {
    return "login";
  }

  @Override
  protected String getRegex() {
    return "name:(.+);password:(.+)";
  }

  @Override
  String getSyntax() {
    return "login name:<name>;password:<password>";
  }

  @Override
  public void execute(Controller controller, String prompt) {
    String name = this.getArg(prompt, 0);
    String password = this.getArg(prompt, 1);
    LoginController loginController = ((LoginController) controller);
    if (loginController.login(name, password)) {
      this.console.writeln("Login realizado correctamente");
      loginController.logged();
    } else {
      this.console.writeln("Nombre o contraseña incorrecto");
    }
  }

}
