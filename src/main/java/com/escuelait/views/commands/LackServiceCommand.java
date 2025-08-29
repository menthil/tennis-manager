package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class LackServiceCommand extends Command {

  LackServiceCommand(Controller controller) {
    super(controller);
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
