package com.escuelait.models;

class SetBuilder {

  private int firstPlayerGames;
  private int secondPlayerGames;

  Set build() {
    Set set = Set.startSet(new TurnBuilder().build());
    for (int gameNumber = 0; gameNumber < this.firstPlayerGames + this.secondPlayerGames; gameNumber++) {
      if (gameNumber % 2 == 0) {
        if (gameNumber / 2 < this.firstPlayerGames) {
          this.firstPlayerWinsGame(set, gameNumber);
        } else {
          this.secondPlayerWinsGame(set, gameNumber);
        }
      } else {
        if (gameNumber / 2 < this.secondPlayerGames) {
          this.secondPlayerWinsGame(set, gameNumber);
        } else {
          this.firstPlayerWinsGame(set, gameNumber);
        }
      }
    }
    return set;
  }

  private void firstPlayerWinsGame(Set set, int gameNumber) {
    int minPointsToWinGame = set.getMinPointsToWinGame();
    for (int i = 0; i < minPointsToWinGame; i++) {
      if (gameNumber % 2 == 0) {
        set.addPointService();
      } else {
        set.addPointRest();
      }
    }
  }

  private void secondPlayerWinsGame(Set set, int gameNumber) {
    int minPointsToWinGame = set.getMinPointsToWinGame();
    for (int i = 0; i < minPointsToWinGame; i++) {
      if (gameNumber % 2 == 0) {
        set.addPointRest();
      } else {
        set.addPointService();
      }
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
