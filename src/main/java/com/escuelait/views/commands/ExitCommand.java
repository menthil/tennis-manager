package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;

class ExitCommand extends Command {

  ExitCommand(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    controller.exit();
  }

}
