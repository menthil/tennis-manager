package com.escuelait.models;

class PlayerBuilder {

  private static int counter = 0;

  static Player createPlayer() {
    PlayerBuilder.counter++;
    return new Player(PlayerBuilder.counter, "Player " + PlayerBuilder.counter);
  }
}
