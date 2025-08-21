package com.escuelait.models;

import java.util.List;

class MatchBuilder {

  private List<Player> players;

  Match build() {
    this.players = new PlayerBuilder().build();
    return Match.createThreeSetMatch(1, this.players);
  }

  Player getFirstPlayer() {
    return this.players.get(0);
  }

  Player getSecondPlayer() {
    return this.players.get(1);
  }

}
