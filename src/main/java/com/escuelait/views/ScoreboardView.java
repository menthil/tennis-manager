package com.escuelait.views;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.escuelait.models.Match;
import com.escuelait.models.Player;
import com.escuelait.utils.Console;

class ScoreboardView {

  private Match match;
  private int numberOfSets;
  private List<Player> players;
  private Console console;

  ScoreboardView(Match match, int numberOfSets, List<Player> players) {
    this.match = match;
    this.numberOfSets = numberOfSets;
    this.players = players;
    this.console = Console.getInstance();
  }

  void write() {
    this.console.writeln("id:" + this.match.getId());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.of("ES"));
    this.console.writeln("date:" + this.match.getCreationDate().format(formatter));
    String playersPoints[] = new String[this.players.size()];
    this.service(playersPoints);
    this.names(playersPoints);
    this.gamePoints(playersPoints);
    this.setGames(playersPoints);
    for (String playerPoints : playersPoints) {
      this.console.writeln(playerPoints);
    }
  }

  private void service(String[] playersPoints) {
    int serving = match.isServing(this.players.get(0)) ? 0 : 1;
    playersPoints[serving] = this.match.isLackService() ? "+" : "*";
    playersPoints[(serving + 1) % 2] = " ";
  }

  private void names(String[] playersPoints) {
    for (int i = 0; i < playersPoints.length; i++) {
      playersPoints[i] += " " + this.players.get(i).name() + ":";
    }
  }

  private void gamePoints(String[] playersPoints) {
    for (int i = 0; i < playersPoints.length; i++) {
      playersPoints[i] += " " + this.match.getGamePoints(this.players.get(i));
    }
  }

  private void setGames(String[] playersPoints) {
    int[][] setGames = this.transformGamesListToArray();
    for (int j = 0; j < this.numberOfSets; j++) {
      boolean isZeroZero = setGames[0][j] == 0 && setGames[1][j] == 0;
      for (int i = 0; i < playersPoints.length; i++) {
        playersPoints[i] += " " + (isZeroZero ? "-" : setGames[i][j]);
      }
    }
  }

  private int[][] transformGamesListToArray() {
    int[][] setGames = new int[this.players.size()][this.numberOfSets];
    for (int i = 0; i < this.players.size(); i++) {
      for (int j = 0; j < this.match.getSetGames(this.players.get(i)).size(); j++) {
        setGames[i][j] = this.match.getSetGames(this.players.get(i)).get(j);
      }
    }
    return setGames;
  }

}
