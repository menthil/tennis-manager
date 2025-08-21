package com.escuelait.models;

import java.util.List;

class TurnBuilder {

  private List<Player> players;

  TurnBuilder() {
    this.players = new PlayerBuilder().build();
  }

  Turn build() {
    return Turn.firstPlayerServes(this.players);
  }

}
