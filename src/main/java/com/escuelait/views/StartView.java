package com.escuelait.views;

import com.escuelait.controllers.StartController;

class StartView extends ConsoleView {

  private StartController startController;

  StartView(StartController startController) {
    this.startController = startController;
  }

  @Override
  void interact() {
    this.console.writeln();
    this.console.writeln("+-----------------------------+");
    this.console.writeln("|                             |");
    this.console.writeln("| Gestor de partidas de tenis |");
    this.console.writeln("|                             |");
    this.console.writeln("+-----------------------------+");
    this.console.writeln();
    this.startController.started();
  }

}
