package com.escuelait.models;

import java.util.ArrayList;

class Set {

  private Turn turn;
  private ArrayList<Game> games;

  private Set(Turn turn) {
    this.turn = turn;
    this.games = new ArrayList<>();
    this.newGame();
  }

  private void newGame() {
    this.games.add(GameFactory.regularGame(this.turn));
  }

  static Set startSet(Turn turn) {
    return new Set(turn);
  }

  int getFirstPlayerResult() {
    return (int) this.games.stream().filter(game -> game.isWinner(this.turn.getFirstPlayer())).count();
  }

  int getSecondPlayerResult() {
    return (int) this.games.stream().filter(game -> game.isWinner(this.turn.getSecondPlayer())).count();
  }

  void addPointService() {
    this.currentGame().addPointService();
    if (this.currentGame().isFinished()) {
      this.newGame();
    }
  }

  private Game currentGame() {
    return this.games.get(this.games.size() - 1);
  }

  void addPointRest() {
    this.currentGame().addPointRest();
    if (this.currentGame().isFinished()) {
      this.newGame();
    }
  }

}
