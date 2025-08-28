package com.escuelait.models;

import java.util.List;
import java.util.Random;

class Turn {

  private List<Player> players;
  private int servingPlayerIndex;

  private Turn(List<Player> players, int servingPlayerIndex) {
    assert players != null && players.size() == 2 && servingPlayerIndex < players.size();
    this.players = players;
    this.servingPlayerIndex = servingPlayerIndex;
  }

  static Turn firstPlayerServes(List<Player> players) {
    return new Turn(players, 0);
  }

  static Turn randomPlayerServes(List<Player> players) {
    assert players != null;
    return new Turn(players, new Random().nextInt(players.size()));
  }

  Player getServicePlayer() {
    return this.getPlayer(this.servingPlayerIndex);
  }

  private Player getPlayer(int index) {
    return this.players.get(index);
  }

  Player getRestPlayer() {
    return this.getPlayer(this.restPlayerIndex());
  }

  private int restPlayerIndex() {
    return (this.servingPlayerIndex + 1) % 2;
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

  Player getOther(Player player) {
    assert this.players.contains(player);
    return player.equals(this.getFirstPlayer()) ? this.getSecondPlayer() : this.getFirstPlayer();
  }

  boolean isServing(Player player) {
    assert this.players.contains(player);
    return player.equals(this.getServicePlayer());
  }

  List<Player> getPlayers() {
    return this.players.stream().toList();
  }

}
