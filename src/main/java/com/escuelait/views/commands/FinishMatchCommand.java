package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class FinishMatchCommand extends Command {

  FinishMatchCommand(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    ((PlayController) this.controller).finishMatch();
    new ScoreboardView(this.controller.getMatch()).write();
  }

}
