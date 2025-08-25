package com.escuelait.views;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ControllerVisitor;
import com.escuelait.controllers.LoginController;
import com.escuelait.controllers.StartController;

public class View implements ControllerVisitor {

  public void interact(Controller controller) {
    controller.accept(this);
  }

  @Override
  public void visit(StartController startController) {
    new StartView(startController).interact();
  }

  @Override
  public void visit(LoginController loginController) {
    new LoginView(loginController).interact();
  }

}
