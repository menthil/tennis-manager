package com.escuelait.views.commands;

import java.util.List;

import com.escuelait.controllers.CommandType;
import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ManageController;

class CreatePlayerCommand extends Command {

  private String name;

  CreatePlayerCommand(Controller controller, String commandString) {
    super(controller);
    this.name = CommandType.CREATE_PLAYER.getArgs(commandString).get(0);
  }

  @Override
  public void execute() {
    ManageController manageController = (ManageController) this.controller;
    List<String> errors = manageController.getCreatePlayerErrors(this.name);
    if (!errors.isEmpty()) {
      for (String error : errors) {
        this.console.writeln(error);
      }
    } else {
      manageController.createPlayer(this.name);
      this.console.writeln("Jugador creado correctamente");
    }
  }

}
