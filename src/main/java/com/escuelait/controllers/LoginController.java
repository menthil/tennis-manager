package com.escuelait.controllers;

import java.util.List;
import java.util.Optional;

import com.escuelait.models.Referee;
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
    assert visitor != null;
    visitor.visit(this);
  }

  @Override
  public List<CommandType> getAvailableCommandTypes() {
    return List.of(CommandType.CREATE_REFEREE, CommandType.LOGIN, CommandType.HELP, CommandType.EXIT);
  }

  public List<String> getCreateRefereeeErrors(String name, String password) {
    assert name != null && password != null;
    if (name.length() < LoginController.MIN_LENGTH) {
      return List.of("La longitud del nombre debe ser al menos " + LoginController.MIN_LENGTH);
    }
    if (password.length() < LoginController.MIN_LENGTH) {
      return List.of("La longitud de la contraseña debe ser al menos " + LoginController.MIN_LENGTH);
    }
    if (this.refereeRepository.findByName(name).isPresent()) {
      return List.of("El árbitro ya existe");
    }
    return List.of();
  }

  public void createReferee(String name, String password) {
    assert this.getCreateRefereeeErrors(name, password).isEmpty();
    this.refereeRepository.create(name, password);
  }

  public boolean login(String name, String password) {
    assert name != null && password != null;
    Optional<Referee> referee = this.refereeRepository.findByName(name);
    if (referee.isEmpty()) {
      return false;
    }
    return referee.get().equals(new Referee(name, password));
  }

  public void logged() {
    this.state.logged();
  }

}
