package com.escuelait.repositories;

import java.util.HashMap;
import java.util.Optional;

import com.escuelait.models.Referee;

public class RefereeRepository {

  private HashMap<String, Referee> referees;

  public RefereeRepository() {
    this.referees = new HashMap<>();
    this.create("molina", "1234");
  }

  public Optional<Referee> findByName(String name) {
    assert name != null;
    return Optional.ofNullable(this.referees.get(name));
  }

  public void create(String name, String password) {
    assert this.findByName(name).isEmpty() && password != null;
    this.referees.put(name, new Referee(name, password));
  }

}
