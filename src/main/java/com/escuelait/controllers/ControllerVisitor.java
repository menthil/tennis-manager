package com.escuelait.controllers;

public interface ControllerVisitor {

  public void visit(StartController startController);

  public void visit(LoginController loginController);

  public void visit(ManageController manageController);

  public void visit(PlayController playController);

}
