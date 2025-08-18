package com.escuelait.models;

class TieBreakGame extends Game {

  protected TieBreakGame(Turn turn) {
    super(turn);
  }

  @Override
  protected boolean shouldChangeService() {
    return (this.getServicePoints() + this.getRestPoints()) % 2 == 1;
  }

}
