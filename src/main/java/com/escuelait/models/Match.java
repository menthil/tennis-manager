package com.escuelait.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Match {

  private static final List<Integer> VALID_NUMBER_OF_SETS = List.of(3, 5);
  private int id;
  private int numberOfSets;
  private List<Set> sets;
  private Turn turn;
  private LocalDate creationDate;

  private Match(int id, int numberOfSets, List<Player> players) {
    this.id = id;
    this.numberOfSets = numberOfSets;
    this.sets = new ArrayList<>();
    this.turn = Turn.firstPlayerServes(players);
    this.startGame();
  }

  private void startGame() {
    this.creationDate = LocalDate.now();
    this.sets.add(Set.start(turn));
  }

  static Match createThreeSetMatch(int id, List<Player> players) {
    return new Match(id, VALID_NUMBER_OF_SETS.get(0), players);
  }

  static Match createFiveSetMatch(int id, List<Player> players) {
    return new Match(id, VALID_NUMBER_OF_SETS.get(1), players);
  }

  void lackService() {
    this.currentSet().lackService();
  }

  int getGamePoints(Player player) {
    return this.currentSet().getGamePoints(player);
  }

  void addPointService() {
    this.currentSet().addPointService();
    this.newSet();
  }

  void addPointRest() {
    this.currentSet().addPointRest();
    this.newSet();
  }

  int[] getFisrstPlayerSetGames() {
    int games[] = new int[this.numberOfSets];
    for (int i = 0; i < this.sets.size(); i++) {
      games[i] = this.sets.get(i).getGamesWon(this.turn.getFirstPlayer());
    }
    return games;
  }

  private Set currentSet() {
    return this.sets.get(this.sets.size() - 1);
  }

  private void newSet() {
    if (this.currentSet().isFinished()) {
      this.sets.add(Set.start(turn));
    }
  }

}
