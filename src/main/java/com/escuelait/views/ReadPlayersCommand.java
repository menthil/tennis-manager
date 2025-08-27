package com.escuelait.views;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ManageController;
import com.escuelait.models.Player;

class ReadPlayersCommand extends Command {

  ReadPlayersCommand(Controller controller) {
    super(controller);
  }

  @Override
  void execute() {
    for (Player player : ((ManageController) this.controller).getPlayers()) {
      this.console.writeln("name:" + player.name() + "; id:" + player.id());
    }
  }

}
