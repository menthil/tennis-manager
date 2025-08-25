package com.escuelait.views;

import com.escuelait.controllers.LoginController;

class LoginView extends ConsoleView {

  private LoginController loginController;

  LoginView(LoginController loginController) {
    this.loginController = loginController;
  }

  @Override
  void interact() {
    String prompt = null;
    do {
      prompt = this.console.readString("Escribe un comando válido> ").trim();
    } while (!this.loginController.isValid(prompt));
    CommandFactory.create(this.loginController.getCommandType(prompt), prompt).execute(this.loginController);
  }

}
