package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.LoginController;

class LoginCommand extends Command {

  private String name;
  private String password;

  LoginCommand(Controller controller, String prompt) {
    super(controller, prompt);
  }

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
  public void execute() {
    this.name = this.getArgs().get(0);
    this.password = this.getArgs().get(1);
    LoginController loginController = ((LoginController) this.controller);
    if (loginController.login(this.name, this.password)) {
      this.console.writeln("Login realizado correctamente");
      loginController.logged();
    } else {
      this.console.writeln("Nombre o contraseña incorrecto");
    }
  }

}
