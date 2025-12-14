package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class PointRestCommand extends Command {

  PointRestCommand(Controller controller, String prompt) {
    super(controller, prompt);
  }

  @Override
  protected String getName() {
    return "pointRest";
  }

  @Override
  protected String getRegex() {
    return "";
  }

  @Override
  String getSyntax() {
    return "pointRest";
  }

  @Override
  public void execute() {
    PlayController playController = (PlayController) this.controller;
    playController.addPointRest();
    new ScoreboardView(playController.getMatch()).write();
    if (playController.isFinished()) {
      playController.logged();
    }
  }

}
