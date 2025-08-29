package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class FinishMatchCommand extends Command {

  FinishMatchCommand(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    PlayController playController = (PlayController) this.controller;
    playController.finishMatch();
    new ScoreboardView(playController.getMatch()).write();
    playController.logged();
  }

}
