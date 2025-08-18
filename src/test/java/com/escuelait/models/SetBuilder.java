package com.escuelait.models;

class SetBuilder {

  private int firstPlayerGames;
  private int secondPlayerGames;

  Set build() {
    Set set = Set.startSet(new TurnBuilder().build());
    for (int i = 0; i < Math.max(this.firstPlayerGames, this.secondPlayerGames); i++) {
      if (i < this.firstPlayerGames) {
        this.firstPlayerWinsGame(set);
      }
      if (i < this.secondPlayerGames) {
        this.secondPlayerWinsGame(set);
      }
    }
    return set;
  }

  private void secondPlayerWinsGame(Set set) {
    for (int i = 0; i < Game.MIN_POINTS_TO_WIN; i++) {
      set.addPointRest();
    }
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

  SetBuilder secondPlayerGames(int games) {
    this.secondPlayerGames = games;
    return this;
  }

}
