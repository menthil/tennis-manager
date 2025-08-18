package com.escuelait.models;

class SetBuilder {

  private int firstPlayerGames;

  Set build() {
    Set set = Set.startSet(new TurnBuilder().build());
    for (int i = 0; i < this.firstPlayerGames; i++) {
      this.firstPlayerWinsGame(set);
    }
    return set;
  }

  private void firstPlayerWinsGame(Set set) {
    for (int i = 0; i < Game.MIN_POINTS_TO_WIN; i++) {
      set.addPointService();
    }
  }

  SetBuilder firstPlayerGames(int games) {
    this.firstPlayerGames = games;
    return this;
  }

}
