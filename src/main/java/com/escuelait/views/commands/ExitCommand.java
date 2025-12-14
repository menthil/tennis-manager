package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;

class ExitCommand extends Command {

  @Override
  protected String getName() {
    return "exit";
  }

  @Override
  protected String getRegex() {
    return "";
  }

  @Override
  String getSyntax() {
    return "exit";
  }

  @Override
  void execute(Controller controller, String prompt) {
    controller.exit();
  }

}
