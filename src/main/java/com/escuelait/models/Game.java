package com.escuelait.models;

class Game {

  private static final int MIN_DIFFERENCE_TO_WIN = 2;
  private static final int MIN_POINTS_TO_WIN = 4;
  private Turn turn;
  private boolean isLackService;
  private Integer[] points;

  private Game(Turn turn) {
    this.turn = turn;
    this.points = new Integer[] { 0, 0 };
  }

  static Game normalGame(Turn turn) {
    return new Game(turn);
  }

  void lackService() {
    if (this.isLackService) {
      this.pointRest();
    }
    this.isLackService = !this.isLackService;
  }

  void pointRest() {
    this.points[1]++;
    this.isLackService = false;
  }

  void pointService() {
    this.points[0]++;
    this.isLackService = false;
    if (this.isServiceWinner()) {
      this.turn.changeService();
    }
  }

  boolean isGameFinished() {
    return (this.getServicePoints() >= MIN_POINTS_TO_WIN || this.getRestPoints() >= MIN_POINTS_TO_WIN)
        && Math.abs(this.getServicePoints() - this.getRestPoints()) >= MIN_DIFFERENCE_TO_WIN;
  }

  int getRestPoints() {
    return this.points[1];
  }

  int getServicePoints() {
    return this.points[0];
  }

  boolean isServiceWinner() {
    return this.isGameFinished() && this.getServicePoints() > this.getRestPoints();
  }

}
