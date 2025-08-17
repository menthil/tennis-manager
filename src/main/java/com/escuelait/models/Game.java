package com.escuelait.models;

class Game {

  private Turn turn;

  private Game(Turn turn) {
    this.turn = turn;
  }

  static Game normalGame(Turn turn) {
    return new Game(turn);
  }

}
