package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;

class LogoutCommand extends Command {

  LogoutCommand(Controller controller, String prompt) {
    super(controller, prompt);
  }

  @Override
  protected String getName() {
    return "logout";
  }

  @Override
  protected String getRegex() {
    return "";
  }

  @Override
  String getSyntax() {
    return "logout";
  }

  @Override
  public void execute() {
    this.controller.started();
  }

}
