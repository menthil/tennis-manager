package com.escuelait.controllers;

import com.escuelait.models.State;

public class LoginController extends Controller {

  LoginController(State state) {
    super(state);
  }

  @Override
  public void accept(ControllerVisitor visitor) {
    visitor.visit(this);
  }

  public void next() {
    this.state.exit();
  }

}
