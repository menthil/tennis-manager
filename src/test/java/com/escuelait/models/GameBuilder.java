package com.escuelait.models;

class GameBuilder {

  private Turn turn;
  private int servicePoints;
  private int restPoints;

  GameBuilder() {
    this.turn = new TurnBuilder().build();
  }

  Game build() {
    Game game = Game.normalGame(this.turn);
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
    this.servicePoints = Game.MIN_POINTS_TO_WIN + 1;
    this.restPoints = Game.MIN_POINTS_TO_WIN;
    return this;
  }

  GameBuilder turn(Turn turn) {
    this.turn = turn;
    return this;
  }

  GameBuilder serviceWins() {
    this.servicePoints = Game.MIN_POINTS_TO_WIN;
    return this;
  }

  GameBuilder restWins() {
    this.restPoints = Game.MIN_POINTS_TO_WIN;
    return this;
  }

}
