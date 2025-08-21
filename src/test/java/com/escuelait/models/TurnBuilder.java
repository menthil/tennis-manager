package com.escuelait.models;

import java.util.List;

class TurnBuilder {

  private List<Player> players;

  TurnBuilder() {
    this.players = new PlayerBuilder().build();
  }

  TurnBuilder players(List<Player> players) {
    this.players = players;
    return this;
  }

  Turn build() {
    return Turn.firstPlayerServes(this.players);
  }

}
