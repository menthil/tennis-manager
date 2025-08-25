package com.escuelait.views;

import com.escuelait.controllers.LoginController;

class LoginView extends ConsoleView {

  private LoginController loginController;

  LoginView(LoginController loginController) {
    this.loginController = loginController;
  }

  @Override
  void interact() {
    this.loginController.next();
  }

}
