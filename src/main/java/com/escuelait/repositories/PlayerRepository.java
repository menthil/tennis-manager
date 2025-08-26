package com.escuelait.repositories;

import java.util.HashMap;
import java.util.Optional;

import com.escuelait.models.Player;

public class PlayerRepository {

  private HashMap<Integer, Player> players;

  public PlayerRepository() {
    this.players = new HashMap<>();
    this.create("Nadal");
    this.create("Alcaraz");
    this.create("Zapata");
  }

  public Optional<Player> findByName(String name) {
    assert name != null;
    for (Player player : this.players.values()) {
      if (player.name().equals(name)) {
        return Optional.of(player);
      }
    }
    return Optional.empty();
  }

  public void create(String name) {
    assert this.findByName(name).isEmpty();
    Player player = new Player(this.players.size() + 1, name);
    this.players.put(player.id(), player);
  }

}
