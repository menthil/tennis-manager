package com.escuelait.models;

class Game {

  private static final int SERVICE = 0;
  private static final int REST = 1;
  private static final int MIN_DIFFERENCE_TO_WIN = 2;
  static final int MIN_POINTS_TO_WIN = 4;
  private Turn turn;
  private boolean isLackService;
  private Integer[] points;

  protected Game(Turn turn) {
    this.turn = turn;
    this.points = new Integer[] { 0, 0 };
  }

  void lackService() {
    if (this.isLackService) {
      this.addPointRest();
    }
    this.isLackService = !this.isLackService;
  }

  void addPointRest() {
    this.addPoint(REST);
  }

  private void addPoint(int i) {
    assert !this.isGameFinished();
    this.points[i]++;
    this.isLackService = false;
    if (this.shouldChangeService()) {
      this.turn.changeService();
    }
  }

  protected boolean shouldChangeService() {
    return this.isGameFinished();
  }

  void addPointService() {
    this.addPoint(SERVICE);
  }

  boolean isGameFinished() {
    return (this.getServicePoints() >= MIN_POINTS_TO_WIN || this.getRestPoints() >= MIN_POINTS_TO_WIN)
        && Math.abs(this.getServicePoints() - this.getRestPoints()) >= MIN_DIFFERENCE_TO_WIN;
  }

  int getRestPoints() {
    return this.points[REST];
  }

  int getServicePoints() {
    return this.points[SERVICE];
  }

  boolean isServiceWinner() {
    return this.isGameFinished() && this.getServicePoints() > this.getRestPoints();
  }

  boolean isRestWinner() {
    return this.isGameFinished() && this.getRestPoints() > this.getServicePoints();
  }

}
