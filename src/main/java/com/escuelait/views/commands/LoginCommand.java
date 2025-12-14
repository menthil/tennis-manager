package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.LoginController;

class LoginCommand extends Command {

  private String name;
  private String password;

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
    this.name = this.getArgs(prompt).get(0);
    this.password = this.getArgs(prompt).get(1);
    LoginController loginController = ((LoginController) controller);
    if (loginController.login(this.name, this.password)) {
      this.console.writeln("Login realizado correctamente");
      loginController.logged();
    } else {
      this.console.writeln("Nombre o contraseña incorrecto");
    }
  }

}
