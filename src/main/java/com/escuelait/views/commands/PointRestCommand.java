package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class PointRestCommand extends Command {

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
  void execute(Controller controller, String prompt) {
    PlayController playController = (PlayController) controller;
    playController.addPointRest();
    new ScoreboardView(playController.getMatch()).write();
    if (playController.isFinished()) {
      playController.logged();
    }
  }

}
