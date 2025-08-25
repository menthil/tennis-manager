package com.escuelait.views;

import com.escuelait.controllers.Controller;

class ExitCommand implements Command {

  @Override
  public void execute(Controller controller) {
    controller.exit();
  }

}
