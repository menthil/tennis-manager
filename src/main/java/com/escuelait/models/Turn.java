package com.escuelait.models;

import java.util.List;
import java.util.Random;

class Turn {

  private List<Player> players;
  private int servingPlayer;

  private Turn(List<Player> players) {
    assert players != null && players.size() == 2;
    this.players = players;
    this.servingPlayer = new Random().nextInt(this.players.size());
  }

  static Turn startMatch(List<Player> players) {
    return new Turn(players);
  }

  Player getServicePlayer() {
    return this.players.get(this.servingPlayer);
  }

  Player getRestPlayer() {
    return this.players.get((this.servingPlayer + 1) % 2);
  }

  void changeService() {
    this.servingPlayer = (this.servingPlayer + 1) % 2;
  }

}
