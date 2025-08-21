package com.escuelait.models;

class TieBreakGame extends Game {

  static final int MIN_POINTS_TO_WIN = 6;

  protected TieBreakGame(Turn turn) {
    super(turn);
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
  protected void addPoint(Player player) {
    super.addPoint(player);
    if (this.isFinished() && !this.turn.getServicePlayer().equals(this.restPlayer)) {
      this.turn.changeService();
    }
  }

}
