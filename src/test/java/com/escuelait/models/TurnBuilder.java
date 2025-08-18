package com.escuelait.models;

import java.util.List;

class TurnBuilder {

  private List<Player> players;

  TurnBuilder() {
    this.players = List.of(PlayerBuilder.createPlayer(), PlayerBuilder.createPlayer());
  }

  Turn build() {
    return Turn.firstPlayerServes(this.players);
  }

}
