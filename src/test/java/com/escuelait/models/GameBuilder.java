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
    return this.service(Game.MIN_POINTS_TO_WIN + 1).rest(Game.MIN_POINTS_TO_WIN);
  }

  GameBuilder turn(Turn turn) {
    this.turn = turn;
    return this;
  }

  GameBuilder serviceWins() {
    return this.service(this.getMinPointsToWin());
  }

  private int getMinPointsToWin() {
    return this.isTieBreak ? TieBreakGame.MIN_POINTS_TO_WIN : Game.MIN_POINTS_TO_WIN;
  }

  GameBuilder restWins() {
    return this.rest(this.getMinPointsToWin());
  }

  GameBuilder tieBreakGame() {
    this.isTieBreak = true;
    return this;
  }

  GameBuilder service(int points) {
    this.servicePoints = points;
    return this;
  }

  GameBuilder rest(int points) {
    this.restPoints = points;
    return this;
  }

}
