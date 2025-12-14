package com.escuelait.views.commands;

import java.util.List;
import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ManageController;
import com.escuelait.utils.DateFormatter;

class CreateMatchCommand extends Command {

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
    int numberOfSets = Integer.parseInt(this.getArg(prompt, 0));
    int ids[] = new int[] {
        Integer.parseInt(this.getArg(prompt, 1)),
        Integer.parseInt(this.getArg(prompt, 2)),
    };
    ManageController manageController = (ManageController) controller;
    List<String> errors = manageController.getCreateMatchErrors(numberOfSets, ids);
    if (!errors.isEmpty()) {
      for (String error : errors) {
        this.console.writeln(error);
      }
    } else {
      manageController.createMatch(numberOfSets, ids);
      this.console.writeln("id:" + controller.getMatch().getId());
      this.console.writeln("date:" + new DateFormatter().format(controller.getMatch().getCreationDate()));
      new ScoreboardView(manageController.getMatch()).write();
    }
  }

}
