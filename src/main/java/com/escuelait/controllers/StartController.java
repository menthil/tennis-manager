package com.escuelait.controllers;

import java.util.List;

import com.escuelait.models.State;

public class StartController extends Controller {

  StartController(State state) {
    super(state);
  }

  @Override
  public void accept(ControllerVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public List<CommandType> getAvailableCommandTypes() {
    return List.of();
  }

}
