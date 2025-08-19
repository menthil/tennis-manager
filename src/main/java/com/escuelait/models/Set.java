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

  static Set startSet(Turn turn) {
    return new Set(turn);
  }

  int getFirstPlayerResult() {
    return this.getPlayerResult(this.turn.getFirstPlayer());
  }

  private int getPlayerResult(Player player) {
    int count = 0;
    for (Game game : this.games) {
      count += game.isWinner(player) ? 1 : 0;
    }
    return count;
  }

  int getSecondPlayerResult() {
    return this.getPlayerResult(this.turn.getSecondPlayer());
  }

  void addPointService() {
    this.currentGame().addPointService();
    this.newGame();
  }

  private Game currentGame() {
    return this.games.get(this.games.size() - 1);
  }

  private void newGame() {
    if (this.currentGame().isFinished() && !this.isFinished()) {
      if (this.games.size() == MIN_GAMES_TO_WIN * 2) {
        this.games.add(GameFactory.tieBreakGame(this.turn));
      } else {
        this.games.add(GameFactory.regularGame(this.turn));
      }
    }
  }

  void addPointRest() {
    this.currentGame().addPointRest();
    this.newGame();
  }

  boolean isFinished() {
    return (this.games.size() == MIN_GAMES_TO_WIN * 2 + 1 && this.currentGame().isFinished()) ||
        (this.getFirstPlayerResult() >= MIN_GAMES_TO_WIN || this.getSecondPlayerResult() >= MIN_GAMES_TO_WIN)
            && Math.abs(this.getFirstPlayerResult() - this.getSecondPlayerResult()) >= MIN_DIFFERENCE_TO_WIN;
  }

  int getMinPointsToWinGame() {
    return this.currentGame().getMinPointsToWin();
  }

}
