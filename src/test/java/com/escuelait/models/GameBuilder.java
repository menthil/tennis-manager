package com.escuelait.models;

class GameBuilder {

  private Turn turn;
  private int servicePoints;
  private int restPoints;
  private boolean isTieBreak;

  GameBuilder() {
    this.turn = new TurnBuilder().build();
  }

  Game build() {
    Game game = this.isTieBreak ? GameFactory.tieBreakGame(this.turn) : GameFactory.regularGame(this.turn);
    for (int i = 0; i < Math.max(this.servicePoints, this.restPoints); i++) {
      if (i < this.servicePoints) {
        game.addPointService();
      }
      if (i < this.restPoints) {
        game.addPointRest();
      }
    }
    return game;
  }

  GameBuilder advantageService() {
    assert !this.isTieBreak;
    this.servicePoints = Game.MIN_POINTS_TO_WIN + 1;
    this.restPoints = Game.MIN_POINTS_TO_WIN;
    return this;
  }

  GameBuilder turn(Turn turn) {
    this.turn = turn;
    return this;
  }

  GameBuilder serviceWins() {
    this.servicePoints = this.isTieBreak ? TieBreakGame.MIN_POINTS_TO_WIN : Game.MIN_POINTS_TO_WIN;
    return this;
  }

  GameBuilder restWins() {
    this.restPoints = Game.MIN_POINTS_TO_WIN;
    return this;
  }

  public GameBuilder tieBreakGame() {
    this.isTieBreak = true;
    return this;
  }

}
