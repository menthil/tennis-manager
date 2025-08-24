package com.escuelait.models;

import java.util.List;

class MatchBuilder {

  private List<Player> players;

  MatchBuilder() {
    this.players = new PlayerBuilder().build();
  }

  Match build() {
    return Match.createThreeSetMatch(1, this.players);
  }

  MatchBuilder players(List<Player> players) {
    this.players = players;
    return this;
  }

}
