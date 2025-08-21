package com.escuelait.models;

import java.util.List;

class SetBuilder {

  private List<Player> players;

  Set build() {
    this.players = new PlayerBuilder().build();
    return Set.start(new TurnBuilder().players(this.players).build());
  }

  Player getFirstPlayer() {
    return this.players.get(0);
  }

  Player getSecondPlayer() {
    return this.players.get(1);
  }

}
