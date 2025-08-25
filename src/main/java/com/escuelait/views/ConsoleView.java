package com.escuelait.views;

import com.escuelait.utils.Console;

abstract class ConsoleView {

  protected Console console;

  ConsoleView() {
    this.console = Console.getInstance();
  }

  abstract void interact();

}
