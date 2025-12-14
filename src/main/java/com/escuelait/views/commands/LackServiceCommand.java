package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class LackServiceCommand extends Command {

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
  void execute(Controller controller, String prompt) {
    PlayController playController = (PlayController) controller;
    playController.lackService();
    new ScoreboardView(playController.getMatch()).write();
    if (playController.isFinished()) {
      playController.logged();
    }
  }

}
