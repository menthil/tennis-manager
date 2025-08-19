package com.escuelait.models;

import java.util.ArrayList;

class Set {

  private static final int MIN_DIFFERENCE_TO_WIN = 2;
  private static final int MIN_GAMES_TO_WIN = 6;
  private Turn turn;
  private ArrayList<Game> games;

  private Set(Turn turn) {
    this.turn = turn;
    this.games = new ArrayList<>();
    this.games.add(GameFactory.regularGame(this.turn));
  }

  private void newGame() {
    if (this.currentGame().isFinished() && !this.isFinished()) {
      this.games.add(GameFactory.regularGame(this.turn));
    }
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
    this.newGame();
  }

  private Game currentGame() {
    return this.games.get(this.games.size() - 1);
  }

  void addPointRest() {
    this.currentGame().addPointRest();
    this.newGame();
  }

  boolean isFinished() {
    return (this.getFirstPlayerResult() >= MIN_GAMES_TO_WIN || this.getSecondPlayerResult() >= MIN_GAMES_TO_WIN)
        && Math.abs(this.getFirstPlayerResult() - this.getSecondPlayerResult()) >= MIN_DIFFERENCE_TO_WIN;
  }

}
