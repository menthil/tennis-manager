package com.escuelait.repositories;

import java.util.HashMap;
import java.util.List;

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
    return match;
  }

}
