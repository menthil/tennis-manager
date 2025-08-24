package com.escuelait.views;

import com.escuelait.controllers.Controller;
import com.escuelait.controllers.ControllerVisitor;
import com.escuelait.controllers.StartController;
import com.escuelait.utils.Console;

public class View implements ControllerVisitor {

  public void interact(Controller controller) {
    controller.accept(this);
  }

  @Override
  public void visit(StartController startController) {
    Console.getInstance().writeln();
    Console.getInstance().writeln("+-----------------------------+");
    Console.getInstance().writeln("|                             |");
    Console.getInstance().writeln("| Gestor de partidas de tenis |");
    Console.getInstance().writeln("|                             |");
    Console.getInstance().writeln("+-----------------------------+");
    Console.getInstance().writeln();
    startController.next();
  }

}
