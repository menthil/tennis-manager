package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class FinishMatchCommand extends Command {

  @Override
  protected String getName() {
    return "finishMatch";
  }

  @Override
  protected String getRegex() {
    return "";
  }

  @Override
  String getSyntax() {
    return "finishMatch";
  }

  @Override
  void execute(Controller controller, String prompt) {
    PlayController playController = (PlayController) controller;
    playController.finishMatch();
    new ScoreboardView(playController.getMatch()).write();
    playController.logged();
  }

}
