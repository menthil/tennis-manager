package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class PointServiceCommand extends Command {

  PointServiceCommand(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    ((PlayController) this.controller).addPointService();
    new ScoreboardView(this.controller.getScore()).write();
  }

}
