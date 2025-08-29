package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class PointRestCommand extends Command {

  PointRestCommand(Controller controller) {
    super(controller);
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
