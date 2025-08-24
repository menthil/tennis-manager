package com.escuelait.controllers;

import com.escuelait.models.State;

public abstract class Controller {

  protected State state;

  Controller(State state) {
    this.state = state;
  }

  public abstract void accept(ControllerVisitor visitor);

}
