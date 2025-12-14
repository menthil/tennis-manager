package com.escuelait.views.commands;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ManageController;
import com.escuelait.models.Player;

class ReadPlayersCommand extends Command {

  ReadPlayersCommand(Controller controller, String prompt) {
    super(controller, prompt);
  }

  @Override
  protected String getName() {
    return "readPlayers";
  }

  @Override
  protected String getRegex() {
    return "";
  }

  @Override
  String getSyntax() {
    return "readPlayers";
  }

  @Override
  public void execute() {
    for (Player player : ((ManageController) this.controller).getPlayers()) {
      this.console.writeln("name:" + player.name() + "; id:" + player.id());
    }
  }

}
