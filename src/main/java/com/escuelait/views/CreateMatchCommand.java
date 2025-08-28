package com.escuelait.views;

import java.util.List;
import com.escuelait.controllers.CommandType;
import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ManageController;

class CreateMatchCommand extends Command {

  private int numberOfSets;
  private int ids[];

  CreateMatchCommand(Controller controller, String commandString) {
    super(controller);
    this.numberOfSets = Integer.parseInt(CommandType.CREATE_MATCH.getArgs(commandString).get(0));
    this.ids = new int[2];
    this.ids[0] = Integer.parseInt(CommandType.CREATE_MATCH.getArgs(commandString).get(1));
    this.ids[1] = Integer.parseInt(CommandType.CREATE_MATCH.getArgs(commandString).get(2));
  }

  @Override
  void execute() {
    ManageController manageController = (ManageController) this.controller;
    List<String> errors = manageController.getCreateMatchErrors(this.numberOfSets, this.ids);
    if (!errors.isEmpty()) {
      for (String error : errors) {
        this.console.writeln(error);
      }
    } else {
      manageController.createMatch(this.numberOfSets, this.ids);
      new ScoreboardView(manageController.getScore()).write();
    }
  }

}
