package com.escuelait.models;

import java.util.List;

class MatchBuilder {

  private List<Player> players;
  private boolean isFiveSetsMatch;

  MatchBuilder() {
    this.players = new PlayerBuilder().build();
  }

  Match build() {
    return this.isFiveSetsMatch
        ? Match.createFiveSetMatch(1, this.players)
        : Match.createThreeSetMatch(1, this.players);
  }

  MatchBuilder players(List<Player> players) {
    this.players = players;
    return this;
  }

  MatchBuilder fiveSetsMatch() {
    this.isFiveSetsMatch = true;
    return this;
  }

}
