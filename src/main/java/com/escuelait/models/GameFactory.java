package com.escuelait.models;

class GameFactory {

  private GameFactory() {
  }

  static Game regularGame(Turn turn) {
    return new Game(turn);
  }

  static Game tieBreakGame(Turn turn) {
    return new TieBreakGame(turn);
  }

}
