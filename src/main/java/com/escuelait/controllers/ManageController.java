package com.escuelait.controllers;

import java.util.List;

import com.escuelait.models.State;

public class ManageController extends Controller {

  ManageController(State state) {
    super(state);
  }

  @Override
  public void accept(ControllerVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  protected List<CommandType> getAvailableCommandTypes() {
    return List.of();
  }

}
