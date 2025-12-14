package com.escuelait.views;

import com.escuelait.controllers.Controller;
import com.escuelait.views.commands.CommandAnalyzer;

class CommandView extends ConsoleView {

  private Controller controller;

  CommandView(Controller controller) {
    assert controller != null;
    this.controller = controller;
  }

  @Override
  void interact() {
    String prompt = null;
    CommandAnalyzer commandAnalyzer = new CommandAnalyzer(this.controller);
    do {
      prompt = this.console.readString(this.getPrompt()).trim();
    } while (!commandAnalyzer.isValid(prompt));
    this.console.writeln();
    commandAnalyzer.execute();
    this.console.writeln();
  }

  private String getPrompt() {
    if (this.controller.isMatchStarted()) {
      return "match id:" + this.controller.getMatch().getId() + "> ";
    }
    return "> ";
  }

}
