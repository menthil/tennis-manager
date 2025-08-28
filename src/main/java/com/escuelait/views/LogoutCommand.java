package com.escuelait.views;

import com.escuelait.controllers.Controller;

class LogoutCommand extends Command {

  LogoutCommand(Controller controller) {
    super(controller);
  }

  @Override
  void execute() {
    this.controller.started();
  }

}
