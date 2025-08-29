package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class LackServiceCommand extends Command {

  LackServiceCommand(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    ((PlayController) this.controller).lackService();
    new ScoreboardView(this.controller.getMatch()).write();
  }

}
