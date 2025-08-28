package com.escuelait.controllers;

import java.util.HashMap;

import com.escuelait.models.State;
import com.escuelait.models.StateValue;
import com.escuelait.repositories.MatchRepository;
import com.escuelait.repositories.PlayerRepository;
import com.escuelait.repositories.RefereeRepository;

public class Logic {

  private State state;
  private HashMap<StateValue, Controller> controllers;

  public Logic() {
    this.state = new State();
    this.controllers = new HashMap<>();
    this.controllers.put(StateValue.INITIAL, new StartController(this.state));
    this.controllers.put(StateValue.STARTED, new LoginController(this.state, new RefereeRepository()));
    MatchRepository matchRepository = new MatchRepository();
    this.controllers.put(StateValue.LOGGED, new ManageController(this.state, new PlayerRepository(), matchRepository));
    this.controllers.put(StateValue.MATCH_STARTED, new PlayController(this.state, matchRepository));
    this.controllers.put(StateValue.EXIT, null);
  }

  public Controller getController() {
    return this.controllers.get(this.state.getValue());
  }

}
