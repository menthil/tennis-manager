package com.escuelait.models;

import java.time.LocalDate;

public class Score {

  private int id;
  private LocalDate creationDate;
  private String services[];
  private String names[];
  private int gamesPoints[];
  private int setsGames[][];

  Score(int id, LocalDate creationDate) {
    this.id = id;
    this.creationDate = creationDate;
    this.services = new String[2];
    this.services[0] = " ";
    this.services[1] = " ";
    this.names = new String[2];
    this.gamesPoints = new int[2];
    this.setsGames = new int[2][];
  }

  void service(int playerNumber) {
    this.services[playerNumber] = "*";
  }

  void lackService(int playerNumber) {
    this.services[playerNumber] = "+";
  }

  void name(String name, int playerNumber) {
    this.names[playerNumber] = name;
  }

  void gamePoints(int gamePoints, int playerNumber) {
    this.gamesPoints[playerNumber] = gamePoints;
  }

  void setGames(int[] setGames, int playerNumber) {
    this.setsGames[playerNumber] = setGames;
  }

  public int getId() {
    return this.id;
  }

  public LocalDate getCreationDate() {
    return this.creationDate;
  }

  public String[] getServices() {
    return this.services;
  }

  public String[] getNames() {
    return this.names;
  }

  public int[] getGamePoints() {
    return this.gamesPoints;
  }

  public int[][] getSetGames() {
    return this.setsGames;
  }

}
