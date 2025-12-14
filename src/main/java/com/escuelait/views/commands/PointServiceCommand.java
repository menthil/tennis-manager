package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class PointServiceCommand extends Command {

  PointServiceCommand(Controller controller, String prompt) {
    super(controller, prompt);
  }

  @Override
  protected String getName() {
    return "pointService";
  }

  @Override
  protected String getRegex() {
    return "";
  }

  @Override
  String getSyntax() {
    return "pointService";
  }

  @Override
  public void execute() {
    PlayController playController = (PlayController) this.controller;
    playController.addPointService();
    new ScoreboardView(playController.getMatch()).write();
    if (playController.isFinished()) {
      playController.logged();
    }
  }

}
