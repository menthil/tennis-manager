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
    return (this.firstPlayerPoints() + this.secondPlayerPoints()) % 2 == 1;
  }

  @Override
  protected int getMinPointsToWin() {
    return MIN_POINTS_TO_WIN;
  }

  @Override
  protected void addPoint(Player player) {
    super.addPoint(player);
    if (this.isFinished() && !this.turn.isServing(this.restPlayer)) {
      this.turn.changeService();
    }
  }

}
