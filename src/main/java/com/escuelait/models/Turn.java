package com.escuelait.models;

import java.util.List;

class Turn {

  private List<Player> players;
  private int servingPlayerIndex;

  private Turn(List<Player> players, Player servingPlayer) {
    this.players = players;
    this.servingPlayerIndex = this.players.indexOf(servingPlayer);
  }

  static Turn firstPlayerServes(List<Player> players) {
    assert players != null && players.size() == 2;
    return new Turn(players, players.get(0));
  }

  Player getServicePlayer() {
    return this.getPlayer(this.servingPlayerIndex);
  }

  Player getRestPlayer() {
    return this.getPlayer(this.restPlayerIndex());
  }

  void changeService() {
    this.servingPlayerIndex = this.restPlayerIndex();
  }

  Player getFirstPlayer() {
    return this.getPlayer(0);
  }

  Player getSecondPlayer() {
    return this.getPlayer(1);
  }

  boolean isFirstPlayerServing() {
    return this.servingPlayerIndex == 0;
  }

  private Player getPlayer(int index) {
    return this.players.get(index);
  }

  private int restPlayerIndex() {
    return (this.servingPlayerIndex + 1) % 2;
  }

}
