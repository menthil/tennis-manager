package com.escuelait.models;

class Game {

  private static final int SERVICE = 0;
  private static final int REST = 1;
  private static final int MIN_DIFFERENCE_TO_WIN = 2;
  static final int MIN_POINTS_TO_WIN = 4;
  protected Turn turn;
  private boolean isLackService;
  private Integer[] points;
  private Player winner;

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

  protected void addPoint(int i) {
    assert !this.isGameFinished();
    this.points[i]++;
    this.isLackService = false;
    this.setWinnerPlayer();
    if (this.shouldChangeService()) {
      this.turn.changeService();
    }
  }

  private void setWinnerPlayer() {
    if (this.isServiceWinner()) {
      this.winner = this.turn.getServicePlayer();
    }
    if (this.isRestWinner()) {
      this.winner = this.turn.getRestPlayer();
    }
  }

  protected boolean shouldChangeService() {
    return this.isGameFinished();
  }

  void addPointService() {
    this.addPoint(SERVICE);
  }

  boolean isGameFinished() {
    return (this.getServicePoints() >= this.getMinPointsToWin() || this.getRestPoints() >= this.getMinPointsToWin())
        && Math.abs(this.getServicePoints() - this.getRestPoints()) >= MIN_DIFFERENCE_TO_WIN;
  }

  protected int getMinPointsToWin() {
    return MIN_POINTS_TO_WIN;
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

  boolean isFirstPlayerWinner() {
    return this.isGameFinished() && this.winner.equals(this.turn.getFirstPlayer());
  }

}
