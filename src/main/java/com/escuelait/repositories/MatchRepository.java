package com.escuelait.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.escuelait.models.Match;
import com.escuelait.models.Player;

public class MatchRepository {

  private HashMap<Integer, Match> matches;

  public MatchRepository() {
    this.matches = new HashMap<>();
  }

  public Match create(int numberOfSets, List<Player> players) {
    assert Match.VALID_NUMBER_OF_SETS.contains(numberOfSets);
    int id = this.matches.size() + 1;
    Match match = Match.createMatch(numberOfSets, id, players);
    this.save(match);
    return match;
  }

  public List<Match> findByPlayer(Player player) {
    return this.matches.values().stream().filter(m -> m.getPlayers().contains(player)).toList();
  }

  public void save(Match match) {
    assert match != null;
    this.matches.put(match.getId(), match);
  }

  public Optional<Match> findById(int id) {
    return Optional.ofNullable(this.matches.get(id));
  }

}
