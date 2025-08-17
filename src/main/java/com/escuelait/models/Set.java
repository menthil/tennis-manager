package com.escuelait.models;

import java.util.ArrayList;

class Set {

  private Turn turn;
  private ArrayList<Game> games;

  private Set(Turn turn) {
    this.turn = turn;
    this.games = new ArrayList<>();
    this.games.add(Game.normalGame(turn));
  }

  static Set startSet(Turn turn) {
    return new Set(turn);
  }

}
