package com.escuelait.controllers;

import java.util.List;

import com.escuelait.models.State;
import com.escuelait.repositories.RefereeRepository;

public class LoginController extends Controller {

  private static final int MIN_LENGTH = 4;
  private RefereeRepository refereeRepository;

  LoginController(State state, RefereeRepository refereeRepository) {
    super(state);
    this.refereeRepository = refereeRepository;
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
    if (this.refereeRepository.containsName(name)) {
      return List.of("El árbitro ya existe");
    }
    return List.of();
  }

  public void createReferee(String name, String password) {
    assert this.getCreateRefereeeErrors(name, password).isEmpty();
    this.refereeRepository.create(name, password);
  }

}
