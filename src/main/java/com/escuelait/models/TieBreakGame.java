package com.escuelait.models;

class TieBreakGame extends Game {

  static final int MIN_POINTS_TO_WIN = 6;
  private Player restPlayer;

  protected TieBreakGame(Turn turn) {
    super(turn);
    this.restPlayer = this.turn.getRestPlayer();
  }

  @Override
  protected boolean shouldChangeService() {
    return (this.getServicePoints() + this.getRestPoints()) % 2 == 1;
  }

  @Override
  protected int getMinPointsToWin() {
    return MIN_POINTS_TO_WIN;
  }

  @Override
  protected void addPoint(int i) {
    super.addPoint(i);
    if (this.isGameFinished() && !this.turn.getServicePlayer().equals(this.restPlayer)) {
      this.turn.changeService();
    }
  }

}
