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

  void addPointService() {
    this.addPoint(SERVICE);
  }

  boolean isFinished() {
    return this.somePlayerReachMinPointsToWin() && this.isDifferenceEnoughToWin();
  }

  int getRestPoints() {
    return this.points[REST];
  }

  int getServicePoints() {
    return this.points[SERVICE];
  }

  boolean isServiceWinner() {
    return this.isFinished() && this.getServicePoints() > this.getRestPoints();
  }

  boolean isRestWinner() {
    return this.isFinished() && this.getRestPoints() > this.getServicePoints();
  }

  boolean isWinner(Player player) {
    return this.isFinished() && this.winner.equals(player);
  }

  protected void addPoint(int i) {
    assert !this.isFinished();
    this.points[i]++;
    this.isLackService = false;
    this.setWinnerPlayer();
    if (this.shouldChangeService()) {
      this.turn.changeService();
    }
  }

  protected boolean shouldChangeService() {
    return this.isFinished();
  }

  protected int getMinPointsToWin() {
    return MIN_POINTS_TO_WIN;
  }

  private void setWinnerPlayer() {
    if (this.isServiceWinner()) {
      this.winner = this.turn.getServicePlayer();
    }
    if (this.isRestWinner()) {
      this.winner = this.turn.getRestPlayer();
    }
  }

  private boolean somePlayerReachMinPointsToWin() {
    return this.getServicePoints() >= this.getMinPointsToWin() || this.getRestPoints() >= this.getMinPointsToWin();
  }

  private boolean isDifferenceEnoughToWin() {
    return Math.abs(this.getServicePoints() - this.getRestPoints()) >= MIN_DIFFERENCE_TO_WIN;
  }

}
