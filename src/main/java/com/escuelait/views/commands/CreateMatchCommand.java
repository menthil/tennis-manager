package com.escuelait.views.commands;

import java.util.List;
import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ManageController;
import com.escuelait.utils.DateFormatter;

class CreateMatchCommand extends Command {

  private int numberOfSets;
  private int ids[];

  @Override
  protected String getName() {
    return "createMatch";
  }

  @Override
  protected String getRegex() {
    return "sets:(.+);ids:(\\d+),(\\d+)";
  }

  @Override
  String getSyntax() {
    return "createMatch sets:<3|5>;ids:<id1>,<id2>";
  }

  @Override
  public void execute(Controller controller, String prompt) {
    this.numberOfSets = Integer.parseInt(this.getArgs(prompt).get(0));
    this.ids = new int[2];
    this.ids[0] = Integer.parseInt(this.getArgs(prompt).get(1));
    this.ids[1] = Integer.parseInt(this.getArgs(prompt).get(2));
    ManageController manageController = (ManageController) controller;
    List<String> errors = manageController.getCreateMatchErrors(this.numberOfSets, this.ids);
    if (!errors.isEmpty()) {
      for (String error : errors) {
        this.console.writeln(error);
      }
    } else {
      manageController.createMatch(this.numberOfSets, this.ids);
      this.console.writeln("id:" + controller.getMatch().getId());
      this.console.writeln("date:" + new DateFormatter().format(controller.getMatch().getCreationDate()));
      new ScoreboardView(manageController.getMatch()).write();
    }
  }

}
