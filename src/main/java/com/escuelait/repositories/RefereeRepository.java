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

  public boolean containsName(String name) {
    return this.referees.containsKey(name);
  }

  public void create(String name, String password) {
    this.referees.put(name, new Referee(name, password));
  }

  public Optional<Referee> findByName(String name) {
    return Optional.ofNullable(this.referees.get(name));
  }

}
