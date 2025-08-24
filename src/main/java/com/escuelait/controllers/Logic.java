package com.escuelait.controllers;

import java.util.HashMap;

import com.escuelait.models.State;
import com.escuelait.models.StateValue;

public class Logic {

  private State state;
  private HashMap<StateValue, Controller> controllers;

  public Logic() {
    this.state = new State();
    this.controllers = new HashMap<>();
    this.controllers.put(StateValue.INITIAL, new StartController(this.state));
    this.controllers.put(StateValue.EXIT, null);
  }

  public Controller getController() {
    return this.controllers.get(this.state.getValue());
  }

}
