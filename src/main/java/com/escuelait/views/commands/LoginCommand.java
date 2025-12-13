package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.LoginController;

class LoginCommand extends Command {

  private String name;
  private String password;

  LoginCommand(Controller controller, String prompt) {
    super(controller);
    this.name = com.escuelait.controllers.Command.LOGIN.getArgs(prompt).get(0);
    this.password = com.escuelait.controllers.Command.LOGIN.getArgs(prompt).get(1);
  }

  @Override
  public void execute() {
    LoginController loginController = ((LoginController) this.controller);
    if (loginController.login(this.name, this.password)) {
      this.console.writeln("Login realizado correctamente");
      loginController.logged();
    } else {
      this.console.writeln("Nombre o contraseña incorrecto");
    }
  }

}
