package com.escuelait.views;

import com.escuelait.controllers.ManageController;

class ManageView extends ConsoleView {

  private ManageController manageController;

  ManageView(ManageController manageController) {
    this.manageController = manageController;
  }

  @Override
  void interact() {
    manageController.exit();
  }

}
