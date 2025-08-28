package com.escuelait.views;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class LackServiceCommand extends Command {

  LackServiceCommand(Controller controller) {
    super(controller);
  }

  @Override
  void execute() {
    ((PlayController) this.controller).lackService();
    new ScoreboardView(this.controller.getScore()).write();
  }

}
