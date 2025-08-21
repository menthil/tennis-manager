package com.escuelait.models;

import java.util.HashMap;
import java.util.Optional;

class Game {

  private static final int MIN_DIFFERENCE_TO_WIN = 2;
  static final int MIN_POINTS_TO_WIN = 4;
  protected Turn turn;
  private boolean isLackService;
  private HashMap<Integer, Integer> points;
  private Player servicePlayer;
  protected Player restPlayer;
  private Player winner;

  protected Game(Turn turn) {
    this.turn = turn;
    this.points = new HashMap<>();
    this.servicePlayer = this.turn.getServicePlayer();
    this.restPlayer = this.turn.getRestPlayer();
  }

  void lackService() {
    if (this.isLackService) {
      this.addPoint(this.restPlayer);
    }
    this.isLackService = !this.isLackService;
  }

  void addPoint(Player player) {
    assert !this.isFinished();
    this.points.put(player.id(), this.getPoints(player) + 1);
    this.isLackService = false;
    if (this.isFinished()) {
      this.winner = this.getServicePoints() > this.getRestPoints() ? this.servicePlayer : this.restPlayer;
    }
    if (this.shouldChangeService()) {
      this.turn.changeService();
    }
  }

  boolean isFinished() {
    return this.somePlayerReachMinPointsToWin() && this.isDifferenceEnoughToWin();
  }

  int getPoints(Player player) {
    return Optional.ofNullable(this.points.get(player.id())).orElse(0);
  }

  boolean isWinner(Player player) {
    return this.isFinished() && this.winner.equals(player);
  }

  protected int getRestPoints() {
    return this.getPoints(this.restPlayer);
  }

  protected int getServicePoints() {
    return this.getPoints(this.servicePlayer);
  }

  protected boolean shouldChangeService() {
    return this.isFinished();
  }

  protected int getMinPointsToWin() {
    return MIN_POINTS_TO_WIN;
  }

  private boolean somePlayerReachMinPointsToWin() {
    return this.getServicePoints() >= this.getMinPointsToWin() || this.getRestPoints() >= this.getMinPointsToWin();
  }

  private boolean isDifferenceEnoughToWin() {
    return Math.abs(this.getServicePoints() - this.getRestPoints()) >= MIN_DIFFERENCE_TO_WIN;
  }

}
