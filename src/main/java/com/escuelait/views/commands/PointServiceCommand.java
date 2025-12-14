package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class PointServiceCommand extends Command {

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
  void execute(Controller controller, String prompt) {
    PlayController playController = (PlayController) controller;
    playController.addPointService();
    new ScoreboardView(playController.getMatch()).write();
    if (playController.isFinished()) {
      playController.logged();
    }
  }

}
