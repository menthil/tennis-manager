package com.escuelait;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.Logic;
import com.escuelait.views.View;

public class Tennis {

  public static void main(String[] args) {
    new Tennis().play();
  }

  private void play() {
    Logic logic = new Logic();
    View view = new View();
    Controller controller = null;
    do {
      controller = logic.getController();
      if (controller != null) {
        view.interact(controller);
      }
    } while (controller != null);
  }

}
