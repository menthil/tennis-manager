package com.escuelait.models;

import java.util.ArrayList;

class Set {

  private static final int MIN_DIFFERENCE_TO_WIN = 2;
  static final int MIN_GAMES_TO_WIN = 6;
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

  int getGamesWon(Player player) {
    int count = 0;
    for (Game game : this.games) {
      count += game.isWinner(player) ? 1 : 0;
    }
    return count;
  }

  void addPoint(Player player) {
    this.currentGame().addPoint(player);
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

  int getGamePoints(Player player) {
    return this.currentGame().getPoints(player);
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
    return this.getFirstPlayerGamesWon() >= MIN_GAMES_TO_WIN || this.getSecondPlayerGamesWon() >= MIN_GAMES_TO_WIN;
  }

  private int getFirstPlayerGamesWon() {
    return this.getGamesWon(this.turn.getFirstPlayer());
  }

  private int getSecondPlayerGamesWon() {
    return this.getGamesWon(this.turn.getSecondPlayer());
  }

  private boolean isDifferenceEnoughToWin() {
    return Math.abs(this.getFirstPlayerGamesWon() - this.getSecondPlayerGamesWon()) >= MIN_DIFFERENCE_TO_WIN;
  }

  private boolean isFinishedTieBreak() {
    return this.games.size() == MAX_REGULAR_GAMES + 1 && this.currentGame().isFinished();
  }

}
