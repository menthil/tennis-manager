package com.escuelait.controllers;

import java.util.HashMap;
import java.util.List;

import com.escuelait.models.State;

public class LoginController extends Controller {

  private static final int MIN_LENGTH = 4;
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

  public List<String> getCreateRefereeeErrors(String name, String password) {
    assert name != null && password != null;
    if (name.length() < LoginController.MIN_LENGTH) {
      return List.of("La longitud del nombre debe ser al menos " + LoginController.MIN_LENGTH);
    }
    if (password.length() < LoginController.MIN_LENGTH) {
      return List.of("La longitud de la contraseña debe ser al menos " + LoginController.MIN_LENGTH);
    }
    if (this.referees.containsKey(name)) {
      return List.of("El árbitro ya existe");
    }
    return List.of();
  }

  public void createReferee(String name, String password) {
    assert this.getCreateRefereeeErrors(name, password).isEmpty();
    this.referees.put(name, password);
  }

}
