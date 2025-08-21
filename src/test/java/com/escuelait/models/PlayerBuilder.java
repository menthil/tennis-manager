package com.escuelait.models;

import java.util.ArrayList;
import java.util.List;

class PlayerBuilder {

  private static final int DEFAULT_NUMBER_OF_PLAYERS = 2;
  private int numberOfPlayers;

  PlayerBuilder() {
    this.numberOfPlayers = DEFAULT_NUMBER_OF_PLAYERS;
  }

  List<Player> build() {
    ArrayList<Player> players = new ArrayList<>();
    for (int i = 0; i < this.numberOfPlayers; i++) {
      players.add(new Player(i, "Player " + i));
    }
    return players;
  }

}
