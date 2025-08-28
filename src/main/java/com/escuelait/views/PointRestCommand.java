package com.escuelait.views;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class PointRestCommand extends Command {

  PointRestCommand(Controller controller) {
    super(controller);
  }

  @Override
  void execute() {
    ((PlayController) this.controller).addPointRest();
    new ScoreboardView(this.controller.getScore()).write();
  }

}
