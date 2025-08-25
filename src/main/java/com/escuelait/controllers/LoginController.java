package com.escuelait.controllers;

import java.util.HashMap;
import java.util.List;

import com.escuelait.models.State;

public class LoginController extends Controller {

  private HashMap<String, String> referees;

  LoginController(State state) {
    super(state);
    this.referees = new HashMap<>();
  }

  @Override
  public void accept(ControllerVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  protected List<CommandType> getAvailableCommandTypes() {
    return List.of(CommandType.CREATE_REFEREE, CommandType.LOGIN, CommandType.EXIT);
  }

  public void createReferee(String name, String password) {
    this.referees.put(name, password);
    System.out.println(this.referees);
  }

}
