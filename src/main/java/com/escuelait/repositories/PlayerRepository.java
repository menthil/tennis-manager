package com.escuelait.repositories;

import java.util.HashMap;

import com.escuelait.models.Player;

public class PlayerRepository {

  private HashMap<Integer, Player> players;

  public PlayerRepository() {
    this.players = new HashMap<>();
    this.create("Nadal");
    this.create("Alcaraz");
    this.create("Zapata");
  }

  public boolean containsName(String name) {
    for (Player player : this.players.values()) {
      if (player.name().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public void create(String name) {
    Player player = new Player(this.players.size() + 1, name);
    this.players.put(player.id(), player);
  }

}
