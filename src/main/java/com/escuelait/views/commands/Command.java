package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.utils.Console;

public abstract class Command {

  protected Controller controller;
  protected Console console;

  Command(Controller controller) {
    this.controller = controller;
    this.console = Console.getInstance();
  }

  public abstract void execute();

}
