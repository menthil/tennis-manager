package com.escuelait.models;

import java.util.HashMap;
import java.util.Optional;

class Game {

  private static final int MIN_DIFFERENCE_TO_WIN = 2;
  static final int MIN_POINTS_TO_WIN = 4;
  protected Turn turn;
  private boolean isLackService;
  private HashMap<Player, Integer> points;

  protected Game(Turn turn) {
    this.turn = turn;
    this.points = new HashMap<>();
  }

  void lackService() {
    if (this.isLackService) {
      this.addPoint(this.turn.getRestPlayer());
    } else {
      this.isLackService = true;
    }
  }

  void addPoint(Player player) {
    assert !this.isFinished();
    this.points.put(player, this.getPoints(player) + 1);
    this.isLackService = false;
    if (this.shouldChangeService()) {
      this.turn.changeService();
    }
  }

  boolean isFinished() {
    return this.somePlayerReachMinPointsToWin() && this.isDifferenceEnoughToWin();
  }

  private boolean somePlayerReachMinPointsToWin() {
    return this.firstPlayerPoints() >= this.minPointsToWin() || this.secondPlayerPoints() >= this.minPointsToWin();
  }

  protected int firstPlayerPoints() {
    return this.getPoints(this.turn.getFirstPlayer());
  }

  int getPoints(Player player) {
    return Optional.ofNullable(this.points.get(player)).orElse(0);
  }

  protected int minPointsToWin() {
    return MIN_POINTS_TO_WIN;
  }

  protected int secondPlayerPoints() {
    return this.getPoints(this.turn.getSecondPlayer());
  }

  private boolean isDifferenceEnoughToWin() {
    return Math.abs(this.firstPlayerPoints() - this.secondPlayerPoints()) >= MIN_DIFFERENCE_TO_WIN;
  }

  protected boolean shouldChangeService() {
    return this.isFinished();
  }

  boolean isWinner(Player player) {
    return this.isFinished() && this.getWinner().equals(player);
  }

  private Player getWinner() {
    return this.firstPlayerPoints() > this.secondPlayerPoints()
        ? this.turn.getFirstPlayer()
        : this.turn.getSecondPlayer();
  }

  boolean isLackService() {
    return this.isLackService;
  }

}
