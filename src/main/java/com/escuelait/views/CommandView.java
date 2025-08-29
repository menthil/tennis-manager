package com.escuelait.views;

import java.util.List;

import com.escuelait.controllers.Controller;
import com.escuelait.views.commands.CommandFactory;

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
      prompt = this.console.readString(this.getPrompt()).trim();
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

  private String getPrompt() {
    if (this.controller.isMatchStarted()) {
      return "match id:" + this.controller.getMatch().getId() + "> ";
    }
    return "> ";
  }

}
