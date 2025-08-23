package com.escuelait.models;

class GameBuilder {

  private Turn turn;
  private boolean isTieBreak;

  GameBuilder() {
    this.turn = new TurnBuilder().build();
  }

  Game build() {
    return this.isTieBreak ? GameFactory.tieBreakGame(this.turn) : GameFactory.regularGame(this.turn);
  }

  GameBuilder turn(Turn turn) {
    this.turn = turn;
    return this;
  }

  GameBuilder tieBreakGame() {
    this.isTieBreak = true;
    return this;
  }

}
