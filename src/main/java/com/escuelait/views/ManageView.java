package com.escuelait.views;

import com.escuelait.controllers.ManageController;

class ManageView extends ConsoleView {

  private ManageController manageController;

  public ManageView(ManageController manageController) {
    this.manageController = manageController;
  }

  @Override
  void interact() {
    manageController.exit();
  }

}
