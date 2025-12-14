package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class FinishMatchCommand extends Command {

  FinishMatchCommand(Controller controller, String prompt) {
    super(controller, prompt);
  }

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
  public void execute() {
    PlayController playController = (PlayController) this.controller;
    playController.finishMatch();
    new ScoreboardView(playController.getMatch()).write();
    playController.logged();
  }

}
