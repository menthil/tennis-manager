package com.escuelait.controllers;

import com.escuelait.models.State;

public class StartController extends Controller {

  StartController(State state) {
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
