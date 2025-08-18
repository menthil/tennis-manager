package com.escuelait.models;

import java.util.ArrayList;
import java.util.List;

class Set {

  private Turn turn;
  private ArrayList<Game> games;

  private Set(Turn turn) {
    this.turn = turn;
    this.games = new ArrayList<>();
    this.games.add(Game.normalGame(Turn.startMatch(List.of(new Player(0, "p1"), new Player(1, "p2")))));
  }

  static Set startSet(Turn turn) {
    return new Set(turn);
  }

}
