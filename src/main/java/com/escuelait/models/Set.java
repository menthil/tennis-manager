package com.escuelait.models;

import java.util.ArrayList;

class Set {

  private static final int MIN_DIFFERENCE_TO_WIN = 2;
  private static final int MIN_GAMES_TO_WIN = 6;
  private static final int MAX_REGULAR_GAMES = MIN_GAMES_TO_WIN * 2;
  private Turn turn;
  private ArrayList<Game> games;

  private Set(Turn turn) {
    this.turn = turn;
    this.games = new ArrayList<>();
    this.games.add(GameFactory.regularGame(this.turn));
  }

  static Set start(Turn turn) {
    return new Set(turn);
  }

  int getFirstPlayerResult() {
    return this.getPlayerResult(this.turn.getFirstPlayer());
  }

  int getSecondPlayerResult() {
    return this.getPlayerResult(this.turn.getSecondPlayer());
  }

  void addPointService() {
    this.currentGame().addPointService();
    this.newGame();
  }

  void addPointRest() {
    this.currentGame().addPointRest();
    this.newGame();
  }

  boolean isFinished() {
    return this.isFinishedBeforeTieBreak() || this.isFinishedTieBreak();
  }

  int getMinPointsToWinGame() {
    return this.currentGame().getMinPointsToWin();
  }

  void lackService() {
    this.currentGame().lackService();
  }

  int getFirstPlayerGamePoints() {
    return this.turn.isFirstPlayerServing()
        ? this.currentGame().getServicePoints()
        : this.currentGame().getRestPoints();
  }

  int getSecondPlayerGamePoints() {
    return !this.turn.isFirstPlayerServing()
        ? this.currentGame().getServicePoints()
        : this.currentGame().getRestPoints();
  }

  private int getPlayerResult(Player player) {
    int count = 0;
    for (Game game : this.games) {
      count += game.isWinner(player) ? 1 : 0;
    }
    return count;
  }

  private Game currentGame() {
    return this.games.get(this.games.size() - 1);
  }

  private void newGame() {
    if (this.currentGame().isFinished() && !this.isFinished()) {
      if (this.games.size() == MAX_REGULAR_GAMES) {
        this.games.add(GameFactory.tieBreakGame(this.turn));
      } else {
        this.games.add(GameFactory.regularGame(this.turn));
      }
    }
  }

  private boolean isFinishedBeforeTieBreak() {
    return this.somePlayerReachMinGamesToWin() && this.isDifferenceEnoughToWin();
  }

  private boolean somePlayerReachMinGamesToWin() {
    return this.getFirstPlayerResult() >= MIN_GAMES_TO_WIN || this.getSecondPlayerResult() >= MIN_GAMES_TO_WIN;
  }

  private boolean isDifferenceEnoughToWin() {
    return Math.abs(this.getFirstPlayerResult() - this.getSecondPlayerResult()) >= MIN_DIFFERENCE_TO_WIN;
  }

  private boolean isFinishedTieBreak() {
    return this.games.size() == MAX_REGULAR_GAMES + 1 && this.currentGame().isFinished();
  }

}
