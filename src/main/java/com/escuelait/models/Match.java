package com.escuelait.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Match {

  public static final List<Integer> VALID_NUMBER_OF_SETS = List.of(3, 5);
  private int id;
  private int numberOfSets;
  private Turn turn;
  private List<Set> sets;
  private LocalDate creationDate;
  private boolean setFinished;

  private Match(int id, int numberOfSets, List<Player> players) {
    this.id = id;
    this.numberOfSets = numberOfSets;
    this.turn = Turn.randomPlayerServes(players);
    this.sets = new ArrayList<>();
    this.sets.add(Set.start(this.turn));
    this.creationDate = LocalDate.now();
  }

  static Match createThreeSetMatch(int id, List<Player> players) {
    return new Match(id, VALID_NUMBER_OF_SETS.get(0), players);
  }

  static Match createFiveSetMatch(int id, List<Player> players) {
    return new Match(id, VALID_NUMBER_OF_SETS.get(1), players);
  }

  public static Match createMatch(int numberOfSets, int id, List<Player> players) {
    assert Match.VALID_NUMBER_OF_SETS.contains(numberOfSets);
    return numberOfSets == VALID_NUMBER_OF_SETS.get(0)
        ? Match.createThreeSetMatch(id, players)
        : Match.createFiveSetMatch(id, players);
  }

  void lackService() {
    this.currentSet().lackService();
    this.newSetIfFinished();
  }

  private Set currentSet() {
    return this.sets.get(this.sets.size() - 1);
  }

  private void newSetIfFinished() {
    if (this.currentSet().isFinished()) {
      this.setFinished = true;
      if (!this.isFinished()) {
        this.sets.add(Set.start(this.turn));
      }
    } else {
      this.setFinished = false;
    }
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
    this.newSetIfFinished();
  }

  void addPointRest() {
    this.addPoint(this.turn.getRestPlayer());
  }

  boolean isGameFinished() {
    return this.currentSet().isGameFinished();
  }

  public List<Integer> getSetGames(Player player) {
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

  public boolean isWinner(Player player) {
    return this.getSetsWon(player) == this.numberOfSets / 2 + 1;
  }

  public int getSetsWon(Player player) {
    int count = 0;
    for (Set set : this.sets) {
      count += set.isWinner(player) ? 1 : 0;
    }
    return count;
  }

  boolean isTieBreak() {
    return this.currentSet().isTieBreak();
  }

  public int getId() {
    return this.id;
  }

  public LocalDate getCreationDate() {
    return this.creationDate;
  }

  public boolean isLackService() {
    return this.currentSet().isLackService();
  }

  Score getScore() {
    Score score = new Score(this.id, this.creationDate);
    this.createPlayerScore(score, 0);
    this.createPlayerScore(score, 1);
    return score;
  }

  private void createPlayerScore(Score score, int playerNumber) {
    Player player = playerNumber == 0 ? this.turn.getFirstPlayer() : this.turn.getSecondPlayer();
    if (this.isServing(player)) {
      if (this.isLackService()) {
        score.lackService(playerNumber);
      } else {
        score.service(playerNumber);
      }
    }
    score.name(player.name(), playerNumber);
    score.gamePoints(this.getGamePoints(player), playerNumber);
    score.setGames(this.transformGamesListToArray(player), playerNumber);
  }

  private int[] transformGamesListToArray(Player player) {
    int[] setGames = new int[this.numberOfSets];
    for (int i = 0; i < this.getSetGames(player).size(); i++) {
      setGames[i] = this.getSetGames(player).get(i);
    }
    return setGames;
  }

  public List<Player> getPlayers() {
    return this.turn.getPlayers();
  }

  public Player getOther(Player player) {
    return this.turn.getOther(player);
  }

  public int getNumberOfSets() {
    return this.numberOfSets;
  }

}
