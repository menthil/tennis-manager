package com.escuelait.views.commands;

import com.escuelait.models.Game;
import com.escuelait.models.Match;
import com.escuelait.models.Player;
import com.escuelait.utils.Console;
import com.escuelait.utils.DateFormatter;

class ScoreboardView {

  private Match match;
  private Console console;

  ScoreboardView(Match match) {
    this.match = match;
    this.console = Console.getInstance();
  }

  void write() {
    this.console.writeln("id:" + this.match.getId());
    this.console.writeln("date:" + new DateFormatter().format(this.match.getCreationDate()));
    for (int i = 0; i < this.match.getPlayers().size(); i++) {
      this.console.writeln(this.getPlayerPoints(i));
    }
    this.writeSpecialPoints();
  }

  private String getPlayerPoints(int i) {
    return this.getServiceMark(i) + " " + this.getPlayer(i).name()
        + ": " + this.getGamePoints(i) + this.getPlayerSetGames(i);
  }

  private String getServiceMark(int i) {
    if (this.match.isFinished()) {
      return "";
    }
    if (this.match.isServing(this.getPlayer(i))) {
      if (this.match.isLackService()) {
        return "+";
      } else {
        return "*";
      }
    }
    return " ";
  }

  private String getPlayerSetGames(int i) {
    String result = "";
    int[][] setGames = this.getSetGames();
    for (int j = 0; j < setGames[i].length; j++) {
      boolean isZeroZero = setGames[0][j] == 0 && setGames[1][j] == 0;
      result += " " + (isZeroZero ? "-" : setGames[i][j]);
    }
    return result;
  }

  private int[][] getSetGames() {
    int[][] setGames = new int[this.match.getPlayers().size()][this.match.getNumberOfSets()];
    for (int i = 0; i < setGames.length; i++) {
      for (int j = 0; j < this.match.getSetGames(this.getPlayer(i)).size(); j++) {
        setGames[i][j] = this.match.getSetGames(this.getPlayer(i)).get(j);
      }
    }
    return setGames;
  }

  private Player getPlayer(int i) {
    return this.match.getPlayers().get(i);
  }

  private String getGamePoints(int i) {
    if (this.match.isGameFinished()) {
      return "0";
    }
    if (this.match.isTieBreak()) {
      return this.match.getGamePoints(this.getPlayer(i)) + "";
    }
    if (this.match.getGamePoints(this.getPlayer(i)) < Game.MIN_POINTS_TO_WIN) {
      return new String[] { "0", "15", "30", "40" }[this.match.getGamePoints(this.getPlayer(i))];
    }
    if (this.match.getGamePoints(this.getPlayer(i)) > this.match.getGamePoints(this.getPlayer((i + 1) % 2))) {
      return "AD";
    }
    return "40";
  }

  private void writeSpecialPoints() {
    if (this.match.isGameFinished()) {
      this.console.write("Game ball!!! ");
      if (this.match.isTieBreak() && !this.match.isFinished()) {
        this.console.write("Tie break!!! ");
      }
    }
    if (this.match.isSetFinished()) {
      this.console.write("Set ball!!! ");
    }
    if (this.match.isFinished()) {
      this.console.write("Match finished!!! ");
    }
    this.console.writeln();
  }

}
