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
    this.turn = Turn.firstPlayerServes(players);
    this.startGame();
  }

  private void startGame() {
    this.creationDate = LocalDate.now();
    this.currentSet = 0;
    this.sets[this.currentSet] = Set.start(turn);
  }

  public static Match createThreeSetMatch(int id, List<Player> players) {
    return new Match(id, VALID_NUMBER_OF_SETS.get(0), players);
  }

  public static Match createFiveSetMatch(int id, List<Player> players) {
    return new Match(id, VALID_NUMBER_OF_SETS.get(1), players);
  }

  public void addPointService() {
    this.sets[this.currentSet].addPointService();
    if (this.sets[this.currentSet].isFinished()) {
      this.currentSet++;
      this.sets[this.currentSet] = Set.start(turn);
    }
  }

  public void addPointRest() {
    this.sets[this.currentSet].addPointRest();
    if (this.sets[this.currentSet].isFinished()) {
      this.currentSet++;
      this.sets[this.currentSet] = Set.start(turn);
    }
  }

  public int[] getFisrstPlayerSetGames() {
    int games[] = new int[this.currentSet + 1];
    for (int i = 0; i < games.length; i++) {
      games[i] = this.sets[i].getFirstPlayerResult();
    }
    return games;
  }

  public void lackService() {
		this.sets[this.currentSet].lackService();
	}

	public int getFirstPlayerGamePoints() {
		return this.sets[this.currentSet].getFirstPlayerGamePoints();
	}

  public int getSecondPlayerGamePoints() {
		return this.sets[this.currentSet].getSecondPlayerGamePoints();
  }

}
