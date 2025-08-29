package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class PointRestCommand extends Command {

  PointRestCommand(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    ((PlayController) this.controller).addPointRest();
    new ScoreboardView(this.controller.getMatch()).write();
  }

}
