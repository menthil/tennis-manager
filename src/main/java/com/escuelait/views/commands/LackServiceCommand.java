package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class LackServiceCommand extends Command {

  LackServiceCommand(Controller controller, String prompt) {
    super(controller, prompt);
  }

  @Override
  protected String getName() {
    return "lackService";
  }

  @Override
  protected String getRegex() {
    return "";
  }

  @Override
  String getSyntax() {
    return "lackService";
  }

  @Override
  public void execute() {
    PlayController playController = (PlayController) this.controller;
    playController.lackService();
    new ScoreboardView(playController.getMatch()).write();
    if (playController.isFinished()) {
      playController.logged();
    }
  }

}
