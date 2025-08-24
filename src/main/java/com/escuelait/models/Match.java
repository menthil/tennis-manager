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
  private boolean setFinished;

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

  private Set currentSet() {
    return this.sets.get(this.sets.size() - 1);
  }

  int getGamePoints(Player player) {
    return this.currentSet().getGamePoints(player);
  }

  boolean isServing(Player player) {
    return this.turn.isServing(player);
  }

  void addPointService() {
    this.addPoint(this.turn.getServicePlayer());
  }

  private void addPoint(Player player) {
    this.currentSet().addPoint(player);
    this.setFinished = false;
    this.newSet();
  }

  private void newSet() {
    if (this.currentSet().isFinished()) {
      this.setFinished = true;
      if (!this.isFinished()) {
        this.sets.add(Set.start(turn));
      }
    }
  }

  void addPointRest() {
    this.addPoint(this.turn.getRestPlayer());
  }

  boolean isGameFinished() {
    return this.currentSet().isGameFinished();
  }

  List<Integer> getSetGames(Player player) {
    ArrayList<Integer> games = new ArrayList<>();
    for (int i = 0; i < this.sets.size(); i++) {
      games.add(this.sets.get(i).getGamesWon(player));
    }
    return games;
  }

  boolean isSetFinished() {
    return this.setFinished;
  }

  boolean isFinished() {
    return this.isWinner(this.turn.getFirstPlayer()) || this.isWinner(this.turn.getSecondPlayer());
  }

  boolean isWinner(Player player) {
    int setsWinner = 0;
    for (Set set : this.sets) {
      setsWinner += set.isWinner(player) ? 1 : 0;
    }
    return setsWinner == this.numberOfSets / 2 + 1;
  }

}
