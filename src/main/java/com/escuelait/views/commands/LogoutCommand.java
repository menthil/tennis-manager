package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;

class LogoutCommand extends Command {

  LogoutCommand(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    this.controller.started();
  }

}
