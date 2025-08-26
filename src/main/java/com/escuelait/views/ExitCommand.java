package com.escuelait.views;

import com.escuelait.controllers.Controller;

class ExitCommand extends Command {

  ExitCommand(Controller controller) {
    super(controller);
  }

  @Override
  void execute() {
    controller.exit();
  }

}
