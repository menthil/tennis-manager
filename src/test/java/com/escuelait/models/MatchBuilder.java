package com.escuelait.models;

import java.util.List;

class MatchBuilder {

  private Player firstPlayer;
  private Player secondPlayer;

  Match build() {
    this.firstPlayer = PlayerBuilder.createPlayer();
    this.secondPlayer = PlayerBuilder.createPlayer();
    return Match.createThreeSetMatch(1, List.of(this.firstPlayer, this.secondPlayer));
  }

  Player getFirstPlayer() {
    return this.firstPlayer;
  }

  Player getSecondPlayer() {
    return this.secondPlayer;
  }

}
