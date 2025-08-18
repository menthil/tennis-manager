package com.escuelait.models;

import java.util.ArrayList;

class Set {

  private Turn turn;
  private ArrayList<Game> games;

  private Set(Turn turn) {
    this.turn = turn;
    this.games = new ArrayList<>();
    this.games.add(GameFactory.regularGame(this.turn));
  }

  static Set startSet(Turn turn) {
    return new Set(turn);
  }

  int getFirstPlayerResult() {
    int gamesWon = 0;
    for (Game game : this.games) {
      if (game.isFirstPlayerWinner()) {
        gamesWon++;
      }
    }
    return gamesWon;
  }

  int getSecondPlayerResult() {
    return 0;
  }

  void addPointService() {
    this.games.get(this.games.size() - 1).addPointService();
    if (this.games.get(this.games.size() - 1).isGameFinished()) {
      this.games.add(GameFactory.regularGame(this.turn));
    }
  }

}
