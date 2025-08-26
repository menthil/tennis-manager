package com.escuelait.views;

import java.util.List;

import com.escuelait.controllers.LoginController;

class LoginView extends ConsoleView {

  private LoginController loginController;

  LoginView(LoginController loginController) {
    this.loginController = loginController;
  }

  @Override
  void interact() {
    String prompt = null;
    List<String> errors = List.of();
    do {
      prompt = this.console.readString("Escribe un comando válido> ").trim();
      errors = this.loginController.getErrors(prompt);
      if (!errors.isEmpty()) {
        for (String error : errors) {
          this.console.writeln(error);
        }
      }
    } while (!errors.isEmpty());
    CommandFactory.create(this.loginController, prompt).execute();
  }

}
