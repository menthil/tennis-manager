package com.escuelait.views;

import com.escuelait.controllers.Controller;
import com.escuelait.utils.Console;

abstract class Command {

  protected Controller controller;
  protected Console console;

  Command(Controller controller) {
    this.controller = controller;
    this.console = Console.getInstance();
  }

  abstract void execute();

}
