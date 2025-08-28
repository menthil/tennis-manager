package com.escuelait.views;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.PlayController;

class FinishMatchCommand extends Command {

  FinishMatchCommand(Controller controller) {
    super(controller);
  }

  @Override
  void execute() {
    ((PlayController) this.controller).finishMatch();
    new ScoreboardView(this.controller.getScore()).write();
  }

}
