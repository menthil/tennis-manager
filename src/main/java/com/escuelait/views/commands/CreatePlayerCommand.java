package com.escuelait.views.commands;

import java.util.List;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ManageController;

class CreatePlayerCommand extends Command {

  private String name;

  @Override
  protected String getName() {
    return "createPlayer";
  }

  @Override
  protected String getRegex() {
    return "name:(.+)";
  }

  @Override
  String getSyntax() {
    return "createPlayer name:<name>";
  }

  @Override
  public void execute(Controller controller, String prompt) {
    this.name = this.getArgs(prompt).get(0);
    ManageController manageController = (ManageController) controller;
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
