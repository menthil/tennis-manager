package com.escuelait.models;

import java.util.List;

class Turn {

  private List<Player> players;
  private int servingPlayer;

  private Turn(List<Player> players, Player servingPlayer) {
    assert players != null && players.size() == 2;
    assert servingPlayer != null && players.contains(servingPlayer);
    this.players = players;
    this.servingPlayer = this.players.indexOf(servingPlayer);
  }

  static Turn startMatch(List<Player> players, Player servingPlayer) {
    return new Turn(players, servingPlayer);
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

  Player getFirstPlayer() {
    return this.players.get(0);
  }

  Player getSecondPlayer() {
    return this.players.get(1);
  }

}
