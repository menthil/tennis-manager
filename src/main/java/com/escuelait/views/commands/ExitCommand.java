package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;

class ExitCommand extends Command {

  ExitCommand(Controller controller, String prompt) {
    super(controller, prompt);
  }

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
  public void execute() {
    controller.exit();
  }

}
