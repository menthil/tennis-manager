package com.escuelait.views;

import java.util.List;

import com.escuelait.controllers.Controller;

class CommandView extends ConsoleView {

  private Controller loginController;

  CommandView(Controller loginController) {
    this.loginController = loginController;
  }

  @Override
  void interact() {
    String prompt = null;
    List<String> errors = List.of();
    do {
      prompt = this.console.readString("Escribe un comando válido (help para mostrar ayuda)> ").trim();
      errors = this.loginController.getErrors(prompt);
      if (!errors.isEmpty()) {
        for (String error : errors) {
          this.console.writeln(error);
        }
      }
    } while (!errors.isEmpty());
    this.console.writeln();
    CommandFactory.create(this.loginController, prompt).execute();
    this.console.writeln();
  }

}
