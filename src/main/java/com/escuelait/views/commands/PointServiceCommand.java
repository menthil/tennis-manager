package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class PointServiceCommand extends Command {

  PointServiceCommand(Controller controller) {
    super(controller);
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
