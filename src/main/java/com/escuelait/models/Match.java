package com.escuelait.models;

import java.time.LocalDate;
import java.util.List;

public class Match {

  private static final List<Integer> VALID_NUMBER_OF_SETS = List.of(3, 5);
  private int id;
  private Set[] sets;
  private Turn turn;
  private LocalDate creationDate;
  private int currentSet;

  private Match(int id, int numberOfSets, List<Player> players) {
    this.id = id;
    this.sets = new Set[numberOfSets];
    this.turn = Turn.startMatch(players);
    this.startGame();
  }

  private void startGame() {
    this.creationDate = LocalDate.now();
    this.currentSet = 0;
    this.sets[this.currentSet] = Set.startSet(turn);
  }

  public static Match createThreeSetMatch(int id, List<Player> players) {
    return new Match(id, VALID_NUMBER_OF_SETS.get(0), players);
  }

  public static Match createFiveSetMatch(int id, List<Player> players) {
    return new Match(id, VALID_NUMBER_OF_SETS.get(1), players);
  }

}
