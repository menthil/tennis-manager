package com.escuelait.models;

import java.util.List;
import java.util.Random;

class Turn {

  private int servingPlayer;

  private Turn(List<Player> players) {
    assert players != null && players.size() == 2;
    this.servingPlayer = new Random().nextInt(players.size()) + 1;
  }

  static Turn startMath(List<Player> players) {
    return new Turn(players);
  }

}
