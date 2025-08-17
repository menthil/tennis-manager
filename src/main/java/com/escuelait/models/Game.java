package com.escuelait.models;

class Game {

  private static final int MIN_DIFFERENCE_TO_WIN = 2;
  private static final int MIN_POINTS_TO_WIN = 4;
  private boolean isLackService;
  private Integer[] points;

  private Game() {
    this.points = new Integer[] { 0, 0 };
  }

  static Game normalGame() {
    return new Game();
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

  Integer[] getGamePoints() {
    return this.points;
  }

  void pointService() {
    this.points[0]++;
    this.isLackService = false;
  }

  boolean isGameFinished() {
    return (this.points[0] >= MIN_POINTS_TO_WIN || this.points[1] >= MIN_POINTS_TO_WIN)
        && Math.abs(this.points[0] - this.points[1]) >= MIN_DIFFERENCE_TO_WIN;
  }

  public boolean isServiceWinner() {
    return this.isGameFinished() && this.points[0] > this.points[1];
  }

}
