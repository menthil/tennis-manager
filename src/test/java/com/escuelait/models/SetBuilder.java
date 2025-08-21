package com.escuelait.models;

import java.util.List;

class SetBuilder {

  private List<Player> players;
  private Turn turn;

  Set build() {
    this.players = new PlayerBuilder().build();
    this.turn = new TurnBuilder().players(this.players).build();
    return Set.start(this.turn);
  }

  Player getFirstPlayer() {
    return this.players.get(0);
  }

  Player getSecondPlayer() {
    return this.players.get(1);
  }

  boolean isServing(Player player) {
    return this.turn.isServing(player);
  }

}
