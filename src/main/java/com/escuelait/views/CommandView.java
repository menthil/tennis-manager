package com.escuelait.views;

import java.util.List;

import com.escuelait.controllers.Controller;

class CommandView extends ConsoleView {

  private Controller controller;

  CommandView(Controller controller) {
    this.controller = controller;
  }

  @Override
  void interact() {
    String prompt = null;
    List<String> errors = List.of();
    do {
      prompt = this.console.readString("Escribe un comando válido (help para mostrar ayuda)> ").trim();
      errors = this.controller.getErrors(prompt);
      if (!errors.isEmpty()) {
        for (String error : errors) {
          this.console.writeln(error);
        }
      }
    } while (!errors.isEmpty());
    this.console.writeln();
    CommandFactory.create(this.controller, prompt).execute();
    this.console.writeln();
  }

}
